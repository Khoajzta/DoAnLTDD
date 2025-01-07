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
import kotlinx.coroutines.launch


class TaiKhoanViewModel:ViewModel() {
    var taikhoan: TaiKhoan? by mutableStateOf(null)
        private set

    fun KiemTraDangNhap(tentaikhoan: String, matkhau: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("TaiKhoanViewModel", "Gửi yêu cầu đến API với tên tài khoản: $tentaikhoan")
                taikhoan = QuanLyBanLaptopRetrofitClient.taiKhoanAPIService.kiemTraTaiKhoan(tentaikhoan, matkhau)
                Log.d("TaiKhoanViewModel", "Dữ liệu trả về: $taikhoan")
            } catch (e: Exception) {
                Log.e("TaiKhoanViewModel", "Lỗi khi lấy dữ liệu từ API", e)
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
}
