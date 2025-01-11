package com.example.lapstore.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lapstore.api.QuanLyBanLaptopRetrofitClient
import com.example.lapstore.models.SanPham
import com.example.lapstore.models.TaiKhoan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.lapstore.models.GioHang
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TaiKhoanViewModel:ViewModel() {
    var taikhoan: TaiKhoan? by mutableStateOf(null)
        private set

    var taikhoanUpdateResult by mutableStateOf("")

    fun KiemTraDangNhap(tentaikhoan: String, matkhau: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("TaiKhoanViewModel", "Gửi yêu cầu đến API với tên tài khoản: $tentaikhoan")
                val result = QuanLyBanLaptopRetrofitClient.taiKhoanAPIService.kiemTraTaiKhoan(tentaikhoan, matkhau)
                withContext(Dispatchers.Main) {
                    taikhoan = result
                }
                Log.d("TaiKhoanViewModel", "Dữ liệu trả về: $taikhoan")
            } catch (e: Exception) {
                Log.e("TaiKhoanViewModel", "Lỗi khi lấy dữ liệu từ API", e)
                withContext(Dispatchers.Main) {
                    taikhoan = null // Xử lý lỗi bằng cách gán giá trị null
                }
            }
        }
    }


    fun getSanTaiKhoanByTentaikhoan(tentaikhoan: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                taikhoan = QuanLyBanLaptopRetrofitClient.taiKhoanAPIService.getTaiKhoanByTentaikhoan(tentaikhoan)
            } catch (e: Exception) {
                Log.e("SanPhamViewModel", "Error getting SanPham", e)
            }
        }
    }

    fun updateTaiKhoan(taiKhoan: TaiKhoan) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.taiKhoanAPIService.updateTaiKhoan(taiKhoan)
                }
                taikhoanUpdateResult = if (response.success) {
                    "Cập nhật thành công: ${response.message}"
                } else {
                    "Cập nhật thất bại: ${response.message}"
                }
            } catch (e: Exception) {
                taikhoanUpdateResult = "Lỗi khi cập nhật giỏ hàng: ${e.message}"
                Log.e("GioHang Error", "Lỗi khi cập nhật giỏ hàng: ${e.message}")
            }
        }
    }
}
