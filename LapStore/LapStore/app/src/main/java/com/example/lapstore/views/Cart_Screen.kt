import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.lapstore.models.GioHang
import com.example.lapstore.viewmodels.GioHangViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavHostController,
    makhachhang: String
) {
    val gioHangViewModel: GioHangViewModel = viewModel()
    val sanPhamViewModel: SanPhamViewModel = viewModel()

    val listGioHang = gioHangViewModel.listGioHang
    val listSanPham = sanPhamViewModel.danhSachSanPhamCuaKhachHang

    var chieucaocard by remember { mutableStateOf(150) }


    var tongtien1sanpham by remember { mutableStateOf(0) }
    var tongtien by remember { mutableStateOf(0) }

    LaunchedEffect(makhachhang) {
        gioHangViewModel.getGioHangByKhachHang(makhachhang.toInt())
        sanPhamViewModel.getSanPhamTheoGioHang(makhachhang.toInt())
    }


    // Tính tổng tiền khi danh sách giỏ hàng hoặc sản phẩm thay đổi
    LaunchedEffect(listGioHang, listSanPham) {
        var total = 0
        for (giohang in listGioHang) {
            val sanpham = listSanPham.firstOrNull { it.MaSanPham == giohang.MaSanPham }
            if (sanpham != null) {
                total += sanpham.Gia * giohang.SoLuong
            }
        }
        tongtien = total
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                ),
                title = {
                    Text(
                        "Giỏ hàng",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                tonalElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tổng: ${formatGiaTien(tongtien)}",
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )

                    Button(
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        onClick = {
                            // Xử lý logic mua hàng với các sản phẩm trong selectedProducts
                        }
                    ) {
                        Text("Mua hàng")
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            items(listGioHang) { giohang ->
                var soLuong by remember { mutableStateOf(giohang.SoLuong) }

                fun updateCart(newSoLuong: Int) {
                    if (newSoLuong != soLuong) {
                        val giohangNew = GioHang(giohang.MaGioHang, giohang.MaKhachHang, giohang.MaSanPham, newSoLuong, 1)
                        gioHangViewModel.updateGioHang(giohangNew)
                        soLuong = newSoLuong
                    }
                }
                for (sanPham in listSanPham) {
                    if (sanPham.MaSanPham == giohang.MaSanPham){
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .height(chieucaocard.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                AsyncImage(
                                    model = sanPham.HinhAnh,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(100.dp),
                                    contentScale = ContentScale.Fit
                                )

                                Column(
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        sanPham.TenSanPham,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = 30.sp
                                    )
                                    Text(
                                        "${formatGiaTien(sanPham.Gia)}",
                                        color = Color.Red
                                    )

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start
                                        ) {
                                            IconButton(
                                                onClick = {
                                                    if (soLuong > 1) {
                                                        updateCart(soLuong - 1)
                                                        gioHangViewModel.getGioHangByKhachHang(makhachhang.toInt())
                                                        tongtien = sanPham.Gia * giohang.SoLuong

                                                    }
                                                }
                                            ) {
                                                Icon(imageVector = Icons.Filled.RemoveCircleOutline, contentDescription = "")
                                            }

                                            Text(soLuong.toString())

                                            IconButton(
                                                onClick = {
                                                    if (soLuong < sanPham.SoLuong) {
                                                        updateCart(soLuong + 1)
                                                        gioHangViewModel.getGioHangByKhachHang(makhachhang.toInt())
                                                        tongtien1sanpham = sanPham.Gia * giohang.SoLuong
                                                    }
                                                }
                                            ) {
                                                Icon(imageVector = Icons.Filled.AddCircleOutline, contentDescription = "")
                                            }
                                        }

                                        if (sanPham.SoLuong <= 5) {
                                            Text(
                                                "Còn lại: ${sanPham.SoLuong}",
                                                color = Color.Red
                                            )
                                        }

                                        IconButton(
                                            onClick = {}
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Delete,
                                                contentDescription = "",
                                                tint = Color.Red
                                            )
                                        }
                                    }

                                    // Hiển thị cảnh báo nếu số lượng vượt quá tồn kho
                                    if (soLuong > sanPham.SoLuong) {
                                        Text(
                                            "Số lượng sản phẩm chỉ còn ${sanPham.SoLuong}",
                                            color = Color.Red
                                        )
                                        chieucaocard = 190
                                    } else {
                                        chieucaocard = 150
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}












