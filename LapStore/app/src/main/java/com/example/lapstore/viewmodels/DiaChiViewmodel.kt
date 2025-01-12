package com.example.lapstore.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lapstore.api.QuanLyBanLaptopRetrofitClient
import com.example.lapstore.models.DiaChi
import com.example.lapstore.models.GioHang
import com.example.lapstore.models.SanPham
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DiaChiViewmodel:ViewModel() {

    var listDiacHi by mutableStateOf<List<DiaChi>>(emptyList())

    var diachi by mutableStateOf<DiaChi?>(null)
        private set

    private val _danhsachDiaChi = MutableStateFlow<List<DiaChi>>(emptyList())
    val danhsachDiaChi: StateFlow<List<DiaChi>> get() = _danhsachDiaChi

    fun getSanPhamByMaDiaChi(madiachi: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                diachi = QuanLyBanLaptopRetrofitClient.diaChiAPIService.getDiaChiByMaDiaChi(madiachi)
            } catch (e: Exception) {
                Log.e("Dia Chi ViewModel", "Error getting Dia Chi", e)
            }
        }
    }

    fun getSanPhamByMaDiaChi2(madiachi: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val diachi = QuanLyBanLaptopRetrofitClient.diaChiAPIService.getDiaChiByMaDiaChi(madiachi)
                _danhsachDiaChi.update { currentList -> currentList + diachi }
            } catch (e: Exception) {
                Log.e("SanPhamViewModel", "Error getting SanPham", e)
            }
        }
    }

    fun getDiaChiKhachHang(MaKhachHang: Int?) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.diaChiAPIService.getDiaChiByMaKhachHang(MaKhachHang)
                }
                listDiacHi = response.diachi
            } catch (e: Exception) {
                Log.e("Dia Chi Error", "Lỗi khi lấy dia chi: ${e.message}")
            }
        }
    }
}