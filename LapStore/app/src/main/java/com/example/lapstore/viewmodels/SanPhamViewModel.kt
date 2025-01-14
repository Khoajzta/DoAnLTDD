import android.util.Log
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
<<<<<<< Updated upstream
import kotlinx.coroutines.launch/**/

class SanPhamViewModel : ViewModel() {
    val allSanPham = liveData(Dispatchers.IO) {
        try {
            val response = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getAllSanPham().execute()
            if (response.isSuccessful) {
                emit(response.body()?.sanpham ?: emptyList())  // Emit dữ liệu vào LiveData
            } else {
                emit(emptyList())
=======
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch/**/
import kotlinx.coroutines.withContext
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.asStateFlow

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

    private val _danhsachSanPham = MutableStateFlow<List<SanPham>>(emptyList())

    private val _products = mutableStateOf<List<SanPham>>(emptyList())
    val products: State<List<SanPham>> = _products

    private val _filteredProducts = mutableStateOf<List<SanPham>>(emptyList())
    val filteredProducts: State<List<SanPham>> = _filteredProducts

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error
    val danhsachSanPham: StateFlow<List<SanPham>> get() = _danhsachSanPham
    private  val _ketquaTimkiem = mutableListOf<SanPham>()
    var searchQuery: String by mutableStateOf("")


    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchError = MutableStateFlow<String?>(null)
    val searchError = _searchError.asStateFlow()

    val tenSanPhamList = mutableStateOf<List<SanPham>>(emptyList())

    private var searchJob: Job? = null
    fun getAllSanPham() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            errorMessage = null
            try {
                val response = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getAllSanPham()
                danhSachAllSanPham = response.sanpham
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
}

=======
    fun getSanPhamById2(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sanPham = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getSanPhamById(id)
                _danhsachSanPham.update { currentList -> currentList + sanPham }
            } catch (e: Exception) {
                Log.e("SanPhamViewModel", "Error getting SanPham", e)
            }
        }
    }

    fun searchSanPham(query: String) {
        searchQuery = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            try {
                _isSearching.value = true
                _searchError.value = null

                if (query.isNotEmpty()) {
                    val response = withContext(Dispatchers.IO) {
                        QuanLyBanLaptopRetrofitClient.sanphamAPIService.searchTenSanPham(query)
                    }
                    // Kiểm tra nếu response.data là null hoặc rỗng
                    if (response.data.isNullOrEmpty()) {
                        _ketquaTimkiem.clear()
                        _searchError.value = "Không có sản phẩm tương tự"
                    } else {
                        _ketquaTimkiem.clear()
                        _ketquaTimkiem.addAll(response.data)
                    }

                } else {
                    _ketquaTimkiem.clear()
                }

            } catch (e: Exception) {
                Log.e("API_ERROR", "Lỗi khi gọi API: ${e.message}")
                _searchError.value = "Không thể tìm kiếm sản phẩm. Vui lòng thử lại sau."
                _ketquaTimkiem.clear()
            } finally {
                _isSearching.value = false
            }
        }
    }
}



>>>>>>> Stashed changes
