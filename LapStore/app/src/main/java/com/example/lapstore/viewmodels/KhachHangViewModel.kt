package com.example.lapstore.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.lapstore.api.QuanLyBanLaptopRetrofitClient
import kotlinx.coroutines.Dispatchers

class KhachHangViewModel: ViewModel() {
    val allKhachHang = liveData(Dispatchers.IO) {
        try {
            // Gọi API lấy danh sách khách hàng
            val response = QuanLyBanLaptopRetrofitClient.khachHangAPIService.getAllKhachHang().execute()
            if (response.isSuccessful) {
                emit(response.body()?.khachhang ?: emptyList())  // Trả dữ liệu vào LiveData
            } else {
                emit(emptyList())  // Trả danh sách trống nếu phản hồi không thành công
            }
        } catch (e: Exception) {
            emit(emptyList())  // Trả danh sách trống nếu có lỗi
        }
    }
}