package com.example.lapstore.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lapstore.api.QuanLyBanLaptopRetrofitClient
import com.example.lapstore.models.GioHang
import com.example.lapstore.models.SanPham
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GioHangViewModel : ViewModel() {
    var listGioHang by mutableStateOf<List<GioHang>>(emptyList())
    var giohangUpdateResult by mutableStateOf("")

    fun getGioHangByKhachHang(MaKhachHang: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.giohangAPIService.getGioHangByKhachHang(
                        MaKhachHang
                    )
                }
                listGioHang = response.giohang
            } catch (e: Exception) {
                Log.e("GioHang Error", "Lỗi khi lấy giỏ hàng: ${e.message}")
            }
        }
    }

    fun updateGioHang(gioHang: GioHang) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.giohangAPIService.updateGioHang(gioHang)
                }
                giohangUpdateResult = if (response.success) {
                    "Cập nhật thành công: ${response.message}"
                } else {
                    "Cập nhật thất bại: ${response.message}"
                }
            } catch (e: Exception) {
                giohangUpdateResult = "Lỗi khi cập nhật giỏ hàng: ${e.message}"
                Log.e("GioHang Error", "Lỗi khi cập nhật giỏ hàng: ${e.message}")
            }
        }
    }
}


