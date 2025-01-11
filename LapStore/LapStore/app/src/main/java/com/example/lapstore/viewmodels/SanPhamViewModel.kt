import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.lapstore.api.QuanLyBanLaptopRetrofitClient
import com.example.lapstore.models.SanPham
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch/**/
import kotlinx.coroutines.withContext

class SanPhamViewModel : ViewModel() {
    var danhSachAllSanPham by mutableStateOf<List<SanPham>>(emptyList())

    var danhSachSanPhamVanPhong by mutableStateOf<List<SanPham>>(emptyList())
        private set
    var danhSachSanPhamGaming by mutableStateOf<List<SanPham>>(emptyList())
        private set
    var danhSachSanPhamCuaKhachHang by mutableStateOf<List<SanPham>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    var sanPham by mutableStateOf<SanPham?>(null)
        private set

    fun getAllSanPham() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            errorMessage = null
            try {
                val response = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getAllSanPham()
                danhSachAllSanPham = response.sanpham
            } catch (e: Exception) {
                errorMessage = e.message
                Log.e("SanPhamViewModel", "Error fetching all products", e)
            } finally {
                isLoading = false
            }
        }
    }

    fun getSanPhamTheoLoai(maLoaiSanPham: Int, isLoai1: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            errorMessage = null
            try {
                val response = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getSanPhamByLoai(maLoaiSanPham)
                if (isLoai1) {
                    danhSachSanPhamVanPhong = response.sanpham
                } else {
                    danhSachSanPhamGaming = response.sanpham
                }
            } catch (e: Exception) {
                errorMessage = e.message
                Log.e("SanPhamViewModel", "Error fetching products", e)
            } finally {
                isLoading = false
            }
        }
    }

    fun getSanPhamTheoGioHang(MaKhachHang: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.sanphamAPIService.getSanPhamByGioHang(MaKhachHang)
                }
                danhSachSanPhamCuaKhachHang = response.sanpham
            } catch (e: Exception) {
                Log.e("SanPham Error", "Lỗi khi lấy sản phẩm: ${e.message}")
            }
        }
    }

    fun getSanPhamById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                sanPham = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getSanPhamById(id)
            } catch (e: Exception) {
                Log.e("SanPhamViewModel", "Error getting SanPham", e)
            }
        }
    }
}
