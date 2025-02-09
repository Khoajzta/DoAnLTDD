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
import com.example.lapstore.models.HoaDonBan
import com.example.lapstore.models.KhachHang
import com.example.lapstore.models.SanPham
import kotlinx.coroutines.Dispatchers
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
>>>>>>> main
import kotlinx.coroutines.launch/**/
import kotlinx.coroutines.withContext

class SanPhamViewModel : ViewModel() {
<<<<<<< HEAD
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
=======
    var danhSachAllSanPham by mutableStateOf<List<SanPham>>(emptyList())
    var danhSachSanPhamTrongHoaDon by mutableStateOf<List<SanPham>>(emptyList())

    private val _danhSachSanPhamGaming = MutableStateFlow<List<SanPham>>(emptyList())
    val danhSachSanPhamGaming: StateFlow<List<SanPham>> = _danhSachSanPhamGaming

    private val _danhSachSanPhamVanPhong = MutableStateFlow<List<SanPham>>(emptyList())
    val danhSachSanPhamVanPhong: StateFlow<List<SanPham>> = _danhSachSanPhamVanPhong

>>>>>>> main
    var danhSachSanPhamCuaKhachHang by mutableStateOf<List<SanPham>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

<<<<<<< HEAD
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
=======
    var sanPhamUpdateResult by mutableStateOf("")
        private set

    var sanPham by mutableStateOf<SanPham?>(null)
        private set

    var danhSach by mutableStateOf<List<SanPham>>(emptyList())

    private val _danhsachSanPham = MutableStateFlow<List<SanPham>>(emptyList())
    val danhsachSanPham: StateFlow<List<SanPham>> get() = _danhsachSanPham

>>>>>>> main
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
<<<<<<< HEAD
>>>>>>> Stashed changes
=======
>>>>>>> main
            }
        }
    }

    fun getSanPhamTheoLoaiGaming() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.sanphamAPIService.getSanPhamTheoLoai(2)
                }
                _danhSachSanPhamGaming.value = response.sanpham ?: emptyList()
            } catch (e: Exception) {
                Log.e("SanPham Error", "Lỗi khi lấy sanpham: ${e.message}")
                _danhSachSanPhamGaming.value = emptyList()
            }
        }
    }

    fun getSanPhamTheoLoaiVanPhong() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.sanphamAPIService.getSanPhamTheoLoai(1)
                }
                _danhSachSanPhamVanPhong.value = response.sanpham ?: emptyList()
            } catch (e: Exception) {
                Log.e("SanPham Error", "Lỗi khi lấy sanpham: ${e.message}")
                _danhSachSanPhamVanPhong.value = emptyList()
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

<<<<<<< HEAD
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
=======
    fun getSanPhamById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                sanPham = QuanLyBanLaptopRetrofitClient.sanphamAPIService.getSanPhamById(id)
            } catch (e: Exception) {
                Log.e("SanPhamViewModel", "Error getting SanPham", e)
            }
        }
    }

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

    fun getSanPhamSearch(search:String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.sanphamAPIService.searchSanPham(search)
                }
                danhSach = response.sanpham
            } catch (e: Exception) {
                Log.e("SanPham Error", "Lỗi khi lấy sản phẩm: ${e.message}")
            }
        }
    }

    fun getSanPhamTrongHoaDon(MaHoaDonBan: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.sanphamAPIService.getSanPhamTheoHoaDon(MaHoaDonBan)
                }
                danhSachSanPhamTrongHoaDon = response.sanpham
            } catch (e: Exception) {
                Log.e("Sản Phẩm Error", "Lỗi khi lấy Sản Phẩm")
            }
        }
    }

    fun clearSanPhamSearch() {
        danhSach = emptyList()
    }

    fun updateSanPham(sanpham: SanPham) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    QuanLyBanLaptopRetrofitClient.sanphamAPIService.updateSanPham(sanpham)
                }
                sanPhamUpdateResult = if (response.success) {
                    "Cập nhật thành công: ${response.message}"
                } else {
                    "Cập nhật thất bại: ${response.message}"
                }
            } catch (e: Exception) {
                sanPhamUpdateResult = "Lỗi khi cập nhật khách hàng: ${e.message}"
                Log.e("SanPham Error", "Lỗi khi cập nhật khách hàng: ${e.message}")
            }
        }
    }
}
>>>>>>> main
