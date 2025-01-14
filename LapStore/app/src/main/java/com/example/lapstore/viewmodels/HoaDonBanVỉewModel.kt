package com.example.lapstore.viewmodels

import HoaDonDeleteRequest
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lapstore.api.QuanLyBanLaptopRetrofitClient
import com.example.lapstore.models.DiaChi
import com.example.lapstore.models.HoaDonBan
import com.example.lapstore.models.SanPham
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HoaDonBanVỉewModel: ViewModel() {
    // Kết quả thêm hóa đơn
    var hoadonAddResult by mutableStateOf("")

    // Thay đổi danh sách hóa đơn thành StateFlow
    private val _danhSachHoaDonCuaKhachHang = MutableStateFlow<List<HoaDonBan>>(emptyList())
    val danhSachHoaDonCuaKhachHang: StateFlow<List<HoaDonBan>> = _danhSachHoaDonCuaKhachHang

    var maHoaDonBan by mutableStateOf(0)

    // Lấy hóa đơn theo khách hàng
    fun getHoaDonTheoKhachHang(MaKhachHang: Int, TrangThai: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.hoaDonBanAPIService.getHoaDoByKhachHang(MaKhachHang, TrangThai)
                }
                _danhSachHoaDonCuaKhachHang.value = response.hoadonban ?: emptyList() // Cập nhật StateFlow
            } catch (e: Exception) {
                Log.e("HoaDon Error", "Lỗi khi lấy hoadon: ${e.message}")
                _danhSachHoaDonCuaKhachHang.value = emptyList() // Gán danh sách rỗng khi có lỗi
            }
        }
    }

    // Thêm hóa đơn
    fun addHoaDon(hoadon: HoaDonBan) {
        viewModelScope.launch {
            try {
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

    // Xóa hóa đơn
    fun deleteHoaDon(mahoadon: Int) {
        viewModelScope.launch {
            try {
                val deleteRequest = HoaDonDeleteRequest(mahoadon)
                val response = QuanLyBanLaptopRetrofitClient.hoaDonBanAPIService.deleteHoaDon(deleteRequest)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse?.message == "Gio Hang Deleted") {
                        Log.d("GioHangViewModel", "Giỏ hàng đã được xóa")
                        // Cập nhật danh sách sau khi xóa
                        _danhSachHoaDonCuaKhachHang.value = _danhSachHoaDonCuaKhachHang.value.filter { it.MaHoaDonBan != mahoadon }
                    } else {
                        Log.e("GioHangViewModel", "Lỗi: ${apiResponse?.message}")
                    }
                } else {
                    Log.e("GioHangViewModel", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("GioHangViewModel", "Exception: ${e.message}")
            }
        }
    }

    // Cập nhật hóa đơn
    fun updateHoaDonBan(hoadon: HoaDonBan) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.hoaDonBanAPIService.updateHoaDon(hoadon)
                }
                hoadonAddResult = if (response.success) {
                    "Cập nhật thành công: ${response.message}"
                } else {
                    "Cập nhật thất bại: ${response.message}"
                }
            } catch (e: Exception) {
                hoadonAddResult = "Lỗi khi cập nhật giỏ hàng: ${e.message}"
                Log.e("GioHang Error", "Lỗi khi cập nhật giỏ hàng: ${e.message}")
            }
        }
    }
}