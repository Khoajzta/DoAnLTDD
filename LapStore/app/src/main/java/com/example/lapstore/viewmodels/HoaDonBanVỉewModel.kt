package com.example.lapstore.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lapstore.api.QuanLyBanLaptopRetrofitClient
import com.example.lapstore.models.HoaDonBan
import com.example.lapstore.models.SanPham
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HoaDonBanVỉewModel:ViewModel() {
    var hoadonAddResult by mutableStateOf("")

    var danhSachHoaDonCuaKhachHang by mutableStateOf<List<HoaDonBan>>(emptyList())
        private set

    fun getHoaDonTheoKhachHang(MaKhachHang: Int, TrangThai: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.hoaDonBanAPIService.getHoaDoByKhachHang(MaKhachHang, TrangThai)
                }
                danhSachHoaDonCuaKhachHang = response.hoadonban ?: emptyList() // Nếu response.hoadonban là null, gán danh sách rỗng
            } catch (e: Exception) {
                Log.e("HoaDon Error", "Lỗi khi lấy hoadon: ${e.message}")
                danhSachHoaDonCuaKhachHang = emptyList() // Gán danh sách rỗng khi có lỗi
            }
        }
    }


    fun addHoaDon(hoadon: HoaDonBan) {
        viewModelScope.launch {
            try {
                // Gọi API để thêm sản phẩm vào giỏ hàng trên server
                val response = QuanLyBanLaptopRetrofitClient.hoaDonBanAPIService.addHoaDonBan(hoadon)
                hoadonAddResult = if (response.success) {
                    "Cập nhật thành công: ${response.message}"
                } else {
                    "Cập nhật thất bại: ${response.message}"
                }
            } catch (e: Exception) {
                Log.e("AddToCart", "Lỗi kết nối: ${e.message}")
            }
        }
    }


}