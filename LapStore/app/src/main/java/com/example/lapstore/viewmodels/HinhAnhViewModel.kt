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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch/**/

class HinhAnhViewModel : ViewModel() {


    fun getSanPhamById(id: String) {
        viewModelScope.launch {
            try {
                // Gọi API và gán kết quả vào _sanPham
                val result = QuanLyBanLaptopRetrofitClient.hinhAnhAPIService.getHinhAnhBySanPham(id)

                Log.d("SanPhamViewModel", "Sản phẩm: $result")
            } catch (e: Exception) {
                Log.e("SanPhamViewModel", "Lỗi khi lấy sản phẩm", e)
            }
        }
    }
}

