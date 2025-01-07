import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lapstore.api.QuanLyBanLaptopRetrofitClient
import com.example.lapstore.models.SanPham
import com.example.lapstore.models.TaiKhoan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaiKhoanViewModel :ViewModel(){
    var taikhoan by mutableStateOf<TaiKhoan?>(null)
        private set

    fun KiemTraDangNhap(tentaikhoan: String, matkhau:String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                taikhoan = QuanLyBanLaptopRetrofitClient.taiKhoanAPIService.getTaiKhoan(tentaikhoan,matkhau)
            } catch (e: Exception) {
                Log.e("TaiKhoanViewModel", "Error getting taikhoan", e)
            }
        }
    }
}