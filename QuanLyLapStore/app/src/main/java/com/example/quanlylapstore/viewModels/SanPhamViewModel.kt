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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch/**/

class SanPhamViewModel : ViewModel() {
    val allSanPham = liveData(Dispatchers.IO) {
        try {
            val response = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getAllSanPham().execute()
            if (response.isSuccessful) {
                emit(response.body()?.sanpham ?: emptyList())
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    val allSanPhamGaming = liveData(Dispatchers.IO) {
        try {
            val response = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getAllSanPhamGaming().execute()
            if (response.isSuccessful) {
                emit(response.body()?.sanpham ?: emptyList())
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    val allSanPhamVanPhong = liveData(Dispatchers.IO) {
        try {
            val response = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getAllSanPhamVanPhong().execute()
            if (response.isSuccessful) {
                emit(response.body()?.sanpham ?: emptyList())
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    fun searchSanPham(search: String) = liveData(Dispatchers.IO) {
        try {
            val response = QuanLyBanLaptopRetrofitClient.sanphamAPIService.searchSanPham(search).execute()
            if (response.isSuccessful) {
                // Emit danh sách sản phẩm tìm thấy
                emit(response.body()?.sanpham ?: emptyList())
            } else {
                emit(emptyList())  // Nếu API trả về lỗi
            }
        } catch (e: Exception) {
            emit(emptyList())  // Nếu có lỗi xảy ra trong quá trình gọi API
        }
    }

    fun deleteSanPham(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {

                val response = QuanLyBanLaptopRetrofitClient.sanphamAPIService.deleteSanPham(id).execute()
                if (response.isSuccessful) {

                }

        }
    }


}

