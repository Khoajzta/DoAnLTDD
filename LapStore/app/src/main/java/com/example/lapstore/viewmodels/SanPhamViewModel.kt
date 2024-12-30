// SanPhamViewModel.kt
package com.example.lapstore.viewmodels
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lapstore.apis.QuanLyBanLaptopRetrofitClient
import com.example.lapstore.models.SanPham
import com.example.lapstore.models.SanPhamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SanPhamViewModel : ViewModel() {
    var listSanpham: List<SanPham> by mutableStateOf(emptyList())

    fun getAllSanPham() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: SanPhamResponse = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getAllSanPham()
                listSanpham = response.sanpham
                Log.d("SanPhamViewModel", "SanPham list: $listSanpham")
            } catch (e: Exception) {
                Log.e("SanPhamViewModel", "Error getting products", e)
            }
        }
    }
}
