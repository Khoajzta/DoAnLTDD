package com.example.lapstore.viewmodels

import DeleteDiaChiRequest
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

    var diachiAddResult by mutableStateOf("")
    var diachiUpdateResult by mutableStateOf("")

    private val _danhsachDiaChi = MutableStateFlow<List<DiaChi>>(emptyList())
    val danhsachDiaChi: StateFlow<List<DiaChi>> get() = _danhsachDiaChi

    fun getDiaChiByMaDiaChi(madiachi: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            diachi = QuanLyBanLaptopRetrofitClient.diaChiAPIService.getDiaChiByMaDiaChi(madiachi)
        }
    }

    fun getDiaChiByMaDiaChi2(madiachi: Int) {
        viewModelScope.launch(Dispatchers.IO) {

                val diachi = QuanLyBanLaptopRetrofitClient.diaChiAPIService.getDiaChiByMaDiaChi(madiachi)
                _danhsachDiaChi.update { currentList -> currentList + diachi }

        }
    }

    fun getDiaChiKhachHang(MaKhachHang: Int?) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.diaChiAPIService.getDiaChiByMaKhachHang(MaKhachHang)
                }
                listDiacHi = response.diachi ?: emptyList() // Gán giá trị mảng rỗng nếu response.diachi null
            } catch (e: Exception) {
                listDiacHi = emptyList()
            }
        }
    }

    fun getDiaChiMacDinh(maKhachHang: Int?, macDinh: Int?) {
        if (maKhachHang == null || macDinh == null) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {

                diachi = QuanLyBanLaptopRetrofitClient.diaChiAPIService.getDiaChiMacDinh(
                    MaKhachHang = maKhachHang,
                    MacDinh = macDinh
                )

        }
    }

    fun addDiaChi(diachi:DiaChi) {
        viewModelScope.launch {
                // Gọi API để thêm sản phẩm vào giỏ hàng trên server
                val response = QuanLyBanLaptopRetrofitClient.diaChiAPIService.addDiaChi(diachi)
                diachiAddResult = if (response.success) {
                    "Cập nhật thành công: ${response.message}"
                } else {
                    "Cập nhật thất bại: ${response.message}"
                }
        }
    }

    fun updateDiaChi(diachi: DiaChi) {
        viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.diaChiAPIService.updateDiaChi(diachi)
                }
                diachiUpdateResult = if (response.success) {
                    "Cập nhật thành công: ${response.message}"
                } else {
                    "Cập nhật thất bại: ${response.message}"
                }

        }
    }

    fun deleteDiaChi(madiachi: Int) {
        viewModelScope.launch {
            try {
                val deleteRequest = DeleteDiaChiRequest(madiachi)
                val response = QuanLyBanLaptopRetrofitClient.diaChiAPIService.deleteDiaChi(deleteRequest)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse?.message == "Dia chi Deleted") {
                        // Cập nhật lại giỏ hàng trong ViewModel
                        listDiacHi = listDiacHi.filter { it.MaDiaChi != madiachi }
                        Log.d("DiaChiViewModel", "Dia chi đã được xóa")
                    } else {
                        Log.e("DiaChiViewModel", "Lỗi: ${apiResponse?.message}")
                    }
                } else {
                    Log.e("DiaChiViewModel", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("DiaChiViewModel", "Exception: ${e.message}")
            }
        }
    }

}