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

class SanPhamViewModel : ViewModel() {

//    var sanpham: SanPham by mutableStateOf(
//        SanPham(
//            0,
//            "",
//            0,
//            1,
//            "",
//            1,
//            "",
//            1,
//            "",
//            1,
//            1,
//            0,
//            "",
//            "",
//            1
//        )
//    )

    var sanPham by mutableStateOf<SanPham?>(null)
        private set

    val allSanPham = liveData(Dispatchers.IO) {
        try {
            val response = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getAllSanPham().execute()
            if (response.isSuccessful) {
                emit(response.body()?.sanpham ?: emptyList())  // Emit dữ liệu vào LiveData
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList())  // Emit danh sách trống nếu có lỗi
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
