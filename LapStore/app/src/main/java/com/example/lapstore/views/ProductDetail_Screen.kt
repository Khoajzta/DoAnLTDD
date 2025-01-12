package com.example.lapstore.views

import HinhAnhViewModel
import SanPhamViewModel
import android.icu.text.DecimalFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.lapstore.models.GioHang
import com.example.lapstore.models.SanPham
import com.example.lapstore.models.TaiKhoan
import com.example.lapstore.viewmodels.GioHangViewModel
import com.example.lapstore.viewmodels.TaiKhoanViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetail_Screen(
    navController: NavHostController,
    id: String,
    makhachhang:String?,
    tentaikhoan:String?,
    viewModel: SanPhamViewModel,
    hinhAnhViewModel: HinhAnhViewModel,
) {
    val systemUiController = rememberSystemUiController()
    var gioHangViewModel:GioHangViewModel = viewModel()

    val danhSachHinhAnh = hinhAnhViewModel.danhsachhinhanhtheosanpham
    val danhsachgiohang = gioHangViewModel.listGioHang

    val sanPham = viewModel.sanPham

    var hinhAnhHienTai by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(makhachhang) {
        if(makhachhang!=null){
            gioHangViewModel.getGioHangByKhachHang(makhachhang.toInt())
        }
    }

    LaunchedEffect(id) {
        if (id.isNotEmpty()) {
            viewModel.getSanPhamById(id)
            hinhAnhViewModel.getHinhAnhTheoSanPham(id)
        }
    }
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Red, darkIcons = false)
    }


    LaunchedEffect(danhSachHinhAnh) {
        if (danhSachHinhAnh.isNotEmpty()) {
            hinhAnhHienTai = danhSachHinhAnh.first().DuongDan
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Red
                ),
                actions = {
                    IconButton(
                        onClick = {
                            if(makhachhang == null && tentaikhoan == null){
                                navController.navigate(NavRoute.LOGINSCREEN.route)
                            }
                            else{
                                navController.navigate("${NavRoute.CART.route}?makhachhang=${makhachhang}&tentaikhoan=${tentaikhoan}")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                title = {
                    // Search Bar
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        OutlinedTextField(
                            value = "",
                            onValueChange = {

                            },
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White
                            ),
                            placeholder = {
                                Text(
                                    text = "Bạn cần tìm gì",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 13.sp
                                    ),
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            },
                            shape = RoundedCornerShape(50)
                        )
                    }
                }

            )

        },
    ) {
        if (sanPham == null || danhSachHinhAnh.isEmpty() || hinhAnhHienTai == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Đang tải dữ liệu...", fontSize = 18.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(17.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                // Hình ảnh sản phẩm chính
                item {
                    AsyncImage(
                        model = hinhAnhHienTai,
                        contentDescription = "Hình ảnh sản phẩm",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                // Danh sách hình ảnh nhỏ trong LazyRow
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(danhSachHinhAnh) { hinhanh ->
                            AsyncImage(
                                model = hinhanh.DuongDan,
                                contentDescription = "Hình ảnh sản phẩm",
                                modifier = Modifier
                                    .height(90.dp)
                                    .width(100.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        hinhAnhHienTai = hinhanh.DuongDan
                                    },
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }

                // Tên sản phẩm
                item {
                    Text(
                        text = sanPham!!.TenSanPham,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        lineHeight = 30.sp
                    )
                }

                // Giá sản phẩm
                item {
                    Text(
                        text = "Giá: ${formatGiaTien(sanPham.Gia)}",
                        fontSize = 20.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left
                    )
                }

                // Nút thêm vào giỏ hàng
                item {
                    Button(
                        onClick = {
                            if (makhachhang == null) {
                                navController.navigate(NavRoute.LOGINSCREEN.route)
                            } else {
                                var giohangnew: GioHang? = null
                                var isProductFound = false

                                for (giohang in danhsachgiohang) {
                                    if (sanPham.MaSanPham == giohang.MaSanPham) {
                                        giohang.SoLuong += 1
                                        gioHangViewModel.updateGioHang(giohang)
                                        isProductFound = true
                                        break
                                    }
                                }

                                // Nếu sản phẩm không tìm thấy trong giỏ hàng thì thêm mới
                                if (!isProductFound) {
                                    giohangnew = GioHang(0, makhachhang.toInt(), sanPham.MaSanPham, 1, 1)
                                    gioHangViewModel.addToCart(giohangnew)
                                }

                                // Làm mới danh sách giỏ hàng
                                gioHangViewModel.getGioHangByKhachHang(makhachhang.toInt())
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0XFF27A4F2)
                        )
                    ) {
                        Text(
                            "THÊM VÀO GIỎ HÀNG",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }

                // Nút mua ngay
                item {
                    Button(
                        onClick = { /* TODO: Xử lý mua ngay */ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        )
                    ) {
                        Text(
                            "MUA NGAY",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }

                // Mô tả sản phẩm
                item {
                    Text(
                        text = "Mô tả sản phẩm",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = sanPham!!.MoTa,
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Thông số kỹ thuật
                item {
                    Text(
                        text = "Thông số kỹ thuật",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("CPU: ${sanPham!!.CPU}")
                        Text("Card đồ họa: ${sanPham!!.CardManHinh}")
                        Text("RAM: ${sanPham!!.RAM} GB")
                        Text("ROM: ${sanPham!!.SSD} GB")
                    }
                }
            }
        }
    }

}


fun formatGiaTien(gia: Int): String {
    val formatter = DecimalFormat("#,###")
    return "${formatter.format(gia)}đ"
}





