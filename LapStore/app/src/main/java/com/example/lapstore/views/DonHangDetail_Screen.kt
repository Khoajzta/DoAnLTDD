import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lapstore.models.SanPham
import com.example.lapstore.viewmodels.ChiTietHoaDonBanViewmodel
import com.example.lapstore.viewmodels.DiaChiViewmodel
import com.example.lapstore.viewmodels.HoaDonBanVỉewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonHangDetailScreen(navController: NavHostController, mahoadon: Int) {

    val hoaDonBanVỉewModel: HoaDonBanVỉewModel = viewModel()
    val chiTietHoaDonBanViewmodel: ChiTietHoaDonBanViewmodel = viewModel()
    val diaChiViewmodel: DiaChiViewmodel = viewModel()
    val sanPhamViewModel: SanPhamViewModel = viewModel()

    // Lấy thông tin địa chỉ và danh sách sản phẩm
    val diachi = diaChiViewmodel.diachi
    val danhSachSanPham: MutableList<SanPham> = remember { mutableStateListOf() }  // Dùng mutableStateListOf()

    // Biến lưu trạng thái đang tải
    val isLoading = remember { mutableStateOf(true) }

    // Lấy danh sách chi tiết hóa đơn khi mã hóa đơn thay đổi
    LaunchedEffect(mahoadon) {
        // Đánh dấu đang tải
        isLoading.value = true

        // Lấy chi tiết hóa đơn
        chiTietHoaDonBanViewmodel.getChiTietHoaDonTheoMaHoaDon(mahoadon)

        // Sau khi API trả về, thay đổi trạng thái tải
        isLoading.value = false
    }

    // Lấy danh sách chi tiết hóa đơn từ ViewModel
    val danhSachChiTietHoaDon = chiTietHoaDonBanViewmodel.danhsachchitethoadon

    // Lấy tất cả sản phẩm khi bắt đầu
    LaunchedEffect(true) {
        sanPhamViewModel.getAllSanPham()
    }

    // Lấy danh sách sản phẩm từ ViewModel
    val danhSachAllSanPham = sanPhamViewModel.danhSachAllSanPham

    // Chờ cho API trả về và thêm sản phẩm vào danh sách
    LaunchedEffect(danhSachChiTietHoaDon) {
        danhSachSanPham.clear()  // Clear trước khi thêm
        for (cthoadon in danhSachChiTietHoaDon) {
            val sanpham = danhSachAllSanPham.find { it.MaSanPham == cthoadon.MaSanPham }
            sanpham?.let { danhSachSanPham.add(it) }  // Thêm vào danh sách
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Thông tin đơn hàng")
                },
                navigationIcon = {
                    IconButton(onClick = { /* Xử lý sự kiện quay lại */ }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "", tint = Color.Red)
                    }
                }
            )
        }
    ) {
        // Hiển thị danh sách sản phẩm
        LazyColumn(modifier = Modifier.padding(it)) {
            items(danhSachSanPham) { sanpham ->
                Text(sanpham.TenSanPham)  // Hiển thị tên sản phẩm
            }
        }
    }
}
