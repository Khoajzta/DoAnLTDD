<<<<<<< HEAD
package com.example.tapdieuhuong

import android.annotation.SuppressLint


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
=======
import android.util.Log
>>>>>>> main
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
<<<<<<< HEAD
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
=======
>>>>>>> main
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
<<<<<<< HEAD
import androidx.compose.foundation.layout.wrapContentSize
=======
>>>>>>> main
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
<<<<<<< HEAD
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
=======
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material3.AlertDialog
>>>>>>> main
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
<<<<<<< HEAD
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
<<<<<<< Updated upstream
import androidx.compose.material3.RadioButtonDefaults
=======
>>>>>>> Stashed changes
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
=======
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
>>>>>>> main
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
<<<<<<< HEAD
<<<<<<< Updated upstream
import androidx.compose.runtime.derivedStateOf
=======
>>>>>>> Stashed changes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
=======
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
>>>>>>> main
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
<<<<<<< HEAD
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
<<<<<<< Updated upstream
import androidx.compose.ui.res.colorResource
=======
>>>>>>> Stashed changes
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lapstore.R



@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val sanPhamStates = remember { mutableStateListOf(false, false, false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TapDieuHuong()
    }
}
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TapDieuHuong() {
    var selectedTabIndexItem by remember { mutableStateOf(0) }
    val tabs = listOf("Đơn hàng", "Chưa Thanh Toán", "Chờ vận chuyển", "Đang vận chuyển", "Đang giao", "Đã giao")
<<<<<<< Updated upstream

    Scaffold(
        topBar = {
            ScrollableTabRow(selectedTabIndexItem,
                edgePadding = 0.dp,
                modifier = Modifier
                , contentColor = Color.Red
            ) {
                tabs.forEachIndexed { index, title ->
=======

    Scaffold(
        topBar = {
            ScrollableTabRow(selectedTabIndexItem,
                edgePadding = 0.dp,
                modifier = Modifier
                , contentColor = Color.Red
            ) {
                tabs.forEachIndexed { index, title ->

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 4.dp)
                            .clip(shape = RectangleShape)
                            .clickable { selectedTabIndexItem = index }

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = title,
                                  fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        },
        content = { padding ->
            when (selectedTabIndexItem) {
                0 -> DonHangScreen(modifier = Modifier.padding(padding))
                1 -> ChuaThanhToanScreen(modifier = Modifier.padding(padding))
                2 -> ChoVanChuyenScreen(modifier = Modifier.padding(padding))
                3 -> DangVanChuyenScreen(modifier = Modifier.padding(padding))
                4 -> DangGiaoScreen(modifier = Modifier.padding(padding))
                5 -> DaGiaoScreen(modifier = Modifier.padding(padding))
            }
        }
    )
}

@Composable
fun DonHangScreen(modifier: Modifier=Modifier) {
    Text(
        text = "Đây là màn hình đơn hàng",
        modifier = Modifier.fillMaxWidth()
    )
    NutThanhToan()
}
@Composable
fun DaGiaoScreen(modifier: Modifier=Modifier) {
    Text(text = "Đây là màn hình Đã giao", modifier = Modifier.fillMaxWidth())
}

@Composable
fun DangGiaoScreen(modifier: Modifier=Modifier) {
    Text(text = "Đây là màn hình Đang giao", modifier = Modifier.fillMaxWidth())
}

@Composable
fun DangVanChuyenScreen(modifier: Modifier=Modifier) {
    Text(text = "Đây là màn hình Đang vận chuyển", modifier = Modifier.fillMaxWidth())
}

@Composable
fun ChoVanChuyenScreen(modifier: Modifier=Modifier) {
    Text(text = "Đây là màn hình Chờ vận chuyển", modifier = Modifier.fillMaxWidth())
}

@Composable
fun ChuaThanhToanScreen(modifier: Modifier=Modifier) {
    Text(text = "Đây là màn hình Chưa Thanh Toán", modifier = Modifier.fillMaxWidth())
}


@Composable
fun NutThanhToan(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Tổng tiền:",
            fontSize = 16.sp,
            color = Color.Black
        )

        Text(
            text = "1300000000000",
            fontSize = 16.sp,
            color = Color.Red
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Nút "Đặt hàng ngay"
    Button(

        onClick = { /* Xử lý đặt hàng */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
    ) {
        Text(
            text = "ĐẶT HÀNG NGAY",
            fontSize = 16.sp,
            color = Color.White
        )
    }
}


@Composable
fun CardScreen() {
    // Trạng thái danh sách sản phẩm
    val products = remember {
        mutableStateListOf(
            Product(
                name = "Laptop gaming Acer Nitro 16 Phoenix AN16 41 R76E",
                Describe="null",
                discountPrice=31300000,
                originalPrice = 29490000,
                quantity = 1,
                gift = "Đế Tản Nhiệt Cooler Master Notepal C3 - Trị giá: 230.000đ",
                imageRes = 1
            )
        )
=======
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.lapstore.models.DiaChi
import com.example.lapstore.viewmodels.DiaChiViewmodel
import com.example.lapstore.viewmodels.GioHangViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavHostController,
    makhachhang: String,
    tentaikhoan: String
) {
    val gioHangViewModel: GioHangViewModel = viewModel()
    val sanPhamViewModel: SanPhamViewModel = viewModel()
    val diaChiViewmodel: DiaChiViewmodel = viewModel()

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Red, darkIcons = false)
    }

    var openDialog by remember { mutableStateOf(false) }


    // Lấy danh sách giỏ hàng và sản phẩm
    val listGioHang = gioHangViewModel.listGioHang
    var danhsachdiahi = diaChiViewmodel.danhsachDiaChi

    // Biến lưu tổng tiền
    var totalPrice by remember { mutableStateOf(0) }

    // Biến lưu trạng thái checkbox của từng sản phẩm
    val selectedItems = remember { mutableStateMapOf<Int, Boolean>() }
    //MaSanPham, Soluong, MaGioHang
    val selectedProducts = remember { mutableListOf<Triple<Int, Int, Int>>() }

    var showDialog by remember { mutableStateOf(false) }

    // Hàm tính tổng tiền
    fun calculateTotalPrice() {
        if (listGioHang.isEmpty()) {
            totalPrice = 0
        } else {
            totalPrice =
                listGioHang.filter { selectedItems[it.MaGioHang] == true }.sumOf { giohang ->
                    val sanPham =
                        sanPhamViewModel.danhSachSanPhamCuaKhachHang.find { it.MaSanPham == giohang.MaSanPham }
                    val gia = sanPham?.Gia ?: 0
                    gia * giohang.SoLuong
                }
        }
    }

    // Lấy dữ liệu và tính tổng tiền ban đầu
    LaunchedEffect(makhachhang) {
        if (makhachhang != null) {
            gioHangViewModel.getGioHangByKhachHang(makhachhang.toInt())
            sanPhamViewModel.getSanPhamTheoGioHang(makhachhang.toInt())
            listGioHang.forEach { selectedItems[it.MaGioHang] = false }
        }
>>>>>>> main
    }
>>>>>>> Stashed changes

<<<<<<< HEAD
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 4.dp)
                            .clip(shape = RectangleShape)
                            .clickable { selectedTabIndexItem = index }

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
<<<<<<< Updated upstream
                            Text(
                                text = title,
                                fontSize = 20.sp
                            )
=======

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
=======
    LaunchedEffect(makhachhang) {
        diaChiViewmodel.getDiaChiKhachHang(makhachhang.toInt())
    }

    LaunchedEffect(listGioHang, sanPhamViewModel.danhSachSanPhamCuaKhachHang) {
        calculateTotalPrice() // Tính tổng tiền khi dữ liệu thay đổi
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text("Giỏ hàng", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Filled.ArrowBackIosNew,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Red)
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = Color.White) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Tổng tiền: ${formatGiaTien(totalPrice)}",
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        fontSize = 20.sp
                    )
                    val selectedProductsString =
                        selectedProducts.joinToString(",") { "${it.first}:${it.second}:${it.third}" }

                    Button(
                        onClick = {
                            if (selectedProducts.isEmpty()) {
                                showDialog = true // Hiển thị thông báo nếu chưa chọn sản phẩm nào
                            } else if (diaChiViewmodel.listDiacHi.isEmpty()) { // Kiểm tra danh sách địa chỉ rỗng
                                openDialog = true // Hiển thị dialog thông báo thêm địa chỉ
                            } else {
                                val selectedProductsString =
                                    selectedProducts.joinToString(",") { "${it.first}:${it.second}:${it.third}" }
                                navController.navigate(
                                    "${NavRoute.PAYSCREEN.route}?selectedProducts=${selectedProductsString}&tongtien=${totalPrice}&tentaikhoan=${tentaikhoan}"
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Mua hàng")
                    }


                    if (openDialog == true) {
                        AlertDialog(
                            containerColor = Color.White,
                            title = {
                                Text("Cập nhật địa chỉ")
                            },
                            onDismissRequest = { openDialog = false }, // Đóng khi nhấn ngoài dialog
                            text = {
>>>>>>> main
                                Text(
                                    "Bạn chưa có địa chỉ giao hàng, vui lòng thêm địa chỉ giao hàng",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        openDialog = false
                                        navController.navigate("${NavRoute.DIACHISCREEN.route}?makhachhang=${makhachhang}")
                                    }
                                ) {
                                    Text(
                                        "Cập nhật địa chỉ",
                                        fontSize = 17.sp,
                                        color = Color.Red
                                    )
                                }
                            },
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        if (showDialog) {
            AlertDialog(
                containerColor = Color.White,
                onDismissRequest = { showDialog = false },
                title = { Text("Thông báo") },
                text = {
                    Text(
                        "Vui lòng chọn sản phẩm để mua.",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(
                            "OK",
                            fontSize = 17.sp,
                            color = Color.Red
                        )
                    }
                }
            )
        }

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(10.dp)
        ) {
            items(listGioHang) { giohang ->
                var soLuong by remember { mutableStateOf(giohang.SoLuong) }

                val sanPham =
                    sanPhamViewModel.danhSachSanPhamCuaKhachHang.find { it.MaSanPham == giohang.MaSanPham }
                var chieucaocard by remember { mutableStateOf(190) }
                if (sanPham != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(chieucaocard.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        onClick = {
                            navController.navigate(NavRoute.PRODUCTDETAILSCREEN.route + "?id=${sanPham.MaSanPham}")
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            // Thêm checkbox
                            Checkbox(
                                checked = selectedItems[giohang.MaGioHang] == true,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color.Red,
                                    uncheckedColor = Color.Red
                                ),
                                onCheckedChange = { isChecked ->
                                    selectedItems[giohang.MaGioHang] = isChecked

                                    if (isChecked) {
                                        // Thêm sản phẩm vào danh sách selectedProducts cùng với MaGioHang
                                        selectedProducts.add(
                                            Triple(
                                                giohang.MaSanPham,
                                                giohang.SoLuong,
                                                giohang.MaGioHang
                                            )
                                        )
                                    } else {
                                        // Loại bỏ sản phẩm khỏi danh sách
                                        selectedProducts.removeAll { it.first == giohang.MaSanPham }
                                    }
                                    // Tính lại tổng tiền sau khi thay đổi trạng thái checkbox
                                    calculateTotalPrice()
                                },
                            )

                            AsyncImage(
                                model = sanPham.HinhAnh,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(120.dp),
                                contentScale = ContentScale.Fit
                            )
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    sanPham.TenSanPham,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 25.sp,
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "${formatGiaTien(sanPham.Gia)}",
                                        color = Color.Red,
                                        fontSize = 17.sp
                                    )

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        IconButton(
                                            onClick = {
                                                if (soLuong > 1) {
                                                    soLuong-- // Giảm số lượng
                                                    giohang.SoLuong = soLuong

                                                    // Cập nhật lại số lượng trong selectedProducts
                                                    val index =
                                                        selectedProducts.indexOfFirst { it.first == giohang.MaSanPham }
                                                    if (index != -1) {
                                                        selectedProducts[index] = Triple(
                                                            giohang.MaSanPham,
                                                            soLuong,
                                                            giohang.MaGioHang
                                                        )
                                                    }

                                                    calculateTotalPrice()
                                                    gioHangViewModel.updateAllGioHang()// Tính lại tổng tiền
                                                }
                                            }
                                        ) {
                                            Icon(
                                                Icons.Filled.RemoveCircleOutline,
                                                contentDescription = null
                                            )
                                        }
                                        Text(soLuong.toString())
                                        IconButton(
                                            onClick = {
                                                if (soLuong < sanPham.SoLuong) {
                                                    soLuong++ // Tăng số lượng
                                                    giohang.SoLuong = soLuong

                                                    // Cập nhật lại số lượng trong selectedProducts
                                                    val index =
                                                        selectedProducts.indexOfFirst { it.first == giohang.MaSanPham }
                                                    if (index != -1) {
                                                        selectedProducts[index] = Triple(
                                                            giohang.MaSanPham,
                                                            soLuong,
                                                            giohang.MaGioHang
                                                        )
                                                    }

                                                    calculateTotalPrice()
                                                    gioHangViewModel.updateAllGioHang()// Tính lại tổng tiền
                                                }
                                            }
                                        ) {
                                            Icon(
                                                Icons.Filled.AddCircleOutline,
                                                contentDescription = null
                                            )
                                        }
                                    }

                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    if (sanPham.SoLuong==0) {
                                        Text(
                                            "(Hết hàng)",
                                            color = Color.Red
                                        )
                                    } else if (soLuong == sanPham.SoLuong) {
                                        Text(
                                            "Chỉ còn ${sanPham.SoLuong} sp",
                                            color = Color.Red
                                        )
                                    } else if(sanPham.SoLuong<=5){
                                        Text(
                                            "Còn ${sanPham.SoLuong} sp",
                                            color = Color.Red
                                        )
                                    }else {
                                        Spacer(modifier = Modifier.width(20.dp))
                                    }
                                    Button(
                                        onClick = {
                                            gioHangViewModel.deleteGioHang(giohang.MaGioHang)
                                            gioHangViewModel.listGioHang =
                                                gioHangViewModel.listGioHang.filter { it.MaGioHang != giohang.MaGioHang }
                                            calculateTotalPrice()
                                        },
                                        modifier = Modifier
                                            .padding(11.dp)
                                            .width(65.dp)
                                            .height(35.dp),
                                        colors = ButtonDefaults.buttonColors(Color.Red),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Delete,
                                            contentDescription = "",
                                            tint = Color.White,
                                        )
                                    }
                                }
                            }
>>>>>>> Stashed changes
                        }
                    }
                }
            }
        },
        content = { padding ->
            when (selectedTabIndexItem) {
                0 -> DonHangScreen(modifier = Modifier.padding(padding))
                1 -> ChuaThanhToanScreen(modifier = Modifier.padding(padding))
                2 -> ChoVanChuyenScreen(modifier = Modifier.padding(padding))
                3 -> DangVanChuyenScreen(modifier = Modifier.padding(padding))
                4 -> DangGiaoScreen(modifier = Modifier.padding(padding))
                5 -> DaGiaoScreen(modifier = Modifier.padding(padding))
            }
        }
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun DonHangScreen(modifier: Modifier = Modifier) {
    // Danh sách trạng thái checkbox cho từng sản phẩm
    val sanPhamStates = remember { mutableStateListOf(false, false, false) }

    // Kiểm tra xem tất cả sản phẩm đã được chọn hay chưa
    val isAllChecked by derivedStateOf { sanPhamStates.all { it } }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Hiển thị danh sách sản phẩm
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Dành chỗ cho NutThanhToan
        ) {
            items(sanPhamStates.size) { index ->
                SanPhamCard(
                    isChecked = sanPhamStates[index],
                    onCheckedChange = { isChecked ->
                        sanPhamStates[index] = isChecked
                    }
                )
            }
        }
<<<<<<< HEAD

        // Nút thanh toán nằm dưới cùng
        NutThanhToan(
            isCheckedAll = isAllChecked,
            onCheckAllChange = { isChecked ->
                sanPhamStates.fill(isChecked)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}


@Composable
fun DaGiaoScreen(modifier: Modifier=Modifier) {
    Text(text = "Đây là màn hình Đã giao", modifier = Modifier.fillMaxWidth())
}

@Composable
fun DangGiaoScreen(modifier: Modifier=Modifier) {
    Text(text = "Đây là màn hình Đang giao", modifier = Modifier.fillMaxWidth())
}

@Composable
fun DangVanChuyenScreen(modifier: Modifier=Modifier) {
    Text(text = "Đây là màn hình Đang vận chuyển", modifier = Modifier.fillMaxWidth())
}

@Composable
fun ChoVanChuyenScreen(modifier: Modifier=Modifier) {
    Text(text = "Đây là màn hình Chờ vận chuyển", modifier = Modifier.fillMaxWidth())
}

@Composable
fun ChuaThanhToanScreen(modifier: Modifier=Modifier) {
    Text(text = "Đây là màn hình Chưa Thanh Toán", modifier = Modifier.fillMaxWidth())
}

@Composable
fun NutThanhToan(
    isCheckedAll: Boolean,
    onCheckAllChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                checked = isCheckedAll,
                onCheckedChange = onCheckAllChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Text(text = "Tất Cả")
            Text(
                text = "Tổng tiền:",
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "1300000000000",
                fontSize = 16.sp,
                color = Color.Red
            )
        }
        Button(

            onClick = { /* Xử lý đặt hàng */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(
                text = "ĐẶT HÀNG NGAY",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}
@Composable
fun SanPhamCard(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Checkbox
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red,
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.White
                )
            )
            Spacer(modifier = Modifier.padding(9.dp))
            // Hình ảnh
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Hình ảnh",
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa ảnh và nội dung

            // Nội dung văn bản và nút
            Column(modifier = Modifier.weight(1f)) { // Chiếm không gian còn lại
                Text(text = "Tên sản phẩm", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Mô tả quà tặng", style = MaterialTheme.typography.bodySmall)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.padding(4.dp)) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            Text(text = "Giá: ", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    // Nút thêm và bớt số lượng
                    Column(modifier = Modifier.padding(8.dp)) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = { /* Xử lý tăng */ },
                                modifier = Modifier.size(32.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                contentPadding = PaddingValues(0.dp),
                                shape = RectangleShape
                            ) {
                                Text("+")
                            }
                            Text(
                                text = "1",
                                modifier = Modifier.padding(horizontal = 8.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Button(
                                onClick = { /* Xử lý giảm */ },
                                modifier = Modifier.size(32.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                contentPadding = PaddingValues(0.dp),
                                shape = RectangleShape
                            ) {
                                Text("-")
                            }
                        }
                    }
                }
            }
        }
    }
}
fun MutableList<Boolean>.fill(value: Boolean) {
    for (i in indices) {
        this[i] = value
    }
}
=======
    }
}




































>>>>>>> main
