package com.example.lapstore.views

import NavRoute
import ProductCard
import SanPhamViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import com.example.lapstore.CategoryMenu
import com.example.lapstore.models.SanPham
import com.example.lapstore.models.TaiKhoan
import com.example.lapstore.viewmodels.TaiKhoanViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: SanPhamViewModel,
    tentaikhoan:String?,
) {

    data class CategoryData(
        val title: String,
        val items: List<String>,
        val icon: ImageVector,
    )

    @Composable
    fun CategoryMenuMain() {
        val categories = listOf(
            CategoryData("LAPTOP", listOf("Thương hiệu", "Giá bán", "CPU Intel - AMD", "Nhu cầu sử dụng"),Icons.Filled.Computer),
            CategoryData("ĐIỆN THOẠI", listOf("Hãng sản xuất", "Giá bán", "Hệ điều hành", "Dung lượng pin"),Icons.Filled.PhoneIphone),
            CategoryData("PHỤ KIỆN", listOf("Tai nghe", "Sạc dự phòng", "Cáp sạc", "Ốp lưng"),Icons.Filled.Headset)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(categories.size) { index ->
                val category = categories[index]
                CategoryMenu(title = category.title, items = category.items, icon = category.icon)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    val systemUiController = rememberSystemUiController()
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val danhSachSanPham = viewModel.danhSachAllSanPham
    val danhSachSanPhamGaming = viewModel.danhSachSanPhamGaming
    val danhSachSanPhamVanPhong = viewModel.danhSachSanPhamVanPhong
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    val taiKhoanViewModel: TaiKhoanViewModel = viewModel()

    val taikhoan = taiKhoanViewModel.taikhoan

    if(tentaikhoan != null){
        taiKhoanViewModel.getSanTaiKhoanByTentaikhoan(tentaikhoan)
    }

    LaunchedEffect(Unit) {
        viewModel.getSanPhamTheoLoai(maLoaiSanPham = 2, isLoai1 = true)
        viewModel.getSanPhamTheoLoai(maLoaiSanPham = 1, isLoai1 = false)
        viewModel.getAllSanPham()
    }
    ModalNavigationDrawer(
        modifier = Modifier.background(Color.White),
        scrimColor = DrawerDefaults.scrimColor,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.White,
                modifier = Modifier.fillMaxHeight().background(Color.White)
            ) {
                SideEffect {
                    systemUiController.setStatusBarColor(color = Color.Red, darkIcons = false)
                }
                Row(
                    modifier = Modifier.fillMaxWidth().background(color = Color.Red),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "DANH MỤC SẢN PHẨM",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = Color.White),
                        modifier = Modifier.padding(10.dp)
                    )
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    ) {
                        Icon(
                            Icons.Filled.ArrowForwardIos,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
                com.example.lapstore.CategoryMenuMain()
                Text("Thông tin", modifier = Modifier.padding(10.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                    Icon(imageVector = Icons.Outlined.SupportAgent, contentDescription = "")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("19001009")
                }
            }
        }
    ){
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                keyboardController?.hide()
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
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
                                navController.navigate(NavRoute.CARD.route)
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
            bottomBar = {
                BottomAppBar(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    tonalElevation = 4.dp
                ) {
                    Column {
                        HorizontalDivider(color = Color.Red)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                IconButton(
                                    modifier = Modifier.size(45.dp),
                                    onClick = {
                                        navController.popBackStack()
                                        navController.navigate(NavRoute.HOME.route)
                                    }
                                ) {
                                    Icon(
                                        Icons.Outlined.Home,
                                        contentDescription = "Profile",
                                        tint = Color.Red
                                    )
                                }
                                Text(
                                    text = "Home",
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                IconButton(
                                    modifier = Modifier.size(45.dp),
                                    onClick = {
                                        keyboardController?.hide()
                                        scope.launch {
                                            drawerState.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        Icons.Outlined.Menu,
                                        contentDescription = "Profile",
                                        tint = Color.Red
                                    )
                                }
                                Text(
                                    text = "Danh mục",
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                IconButton(
                                    modifier = Modifier.size(45.dp),
                                    onClick = { }
                                ) {
                                    Icon(
                                        Icons.Outlined.SupportAgent,
                                        contentDescription = "Profile",
                                        tint = Color.Red
                                    )
                                }
                                Text(
                                    text = "Tư vấn",
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceAround
                            ) {
                                IconButton(
                                    modifier = Modifier.size(45.dp),
                                    onClick = {
                                        if(taikhoan==null){
                                            navController.navigate(NavRoute.LOGINSCREEN.route)
                                        }
                                        else{
                                            navController.navigate(NavRoute.ACCOUNT.route)
                                        }
                                    }
                                ) {
                                    Icon(
                                        Icons.Outlined.Person,
                                        contentDescription = "Profile",
                                        tint = Color.Red
                                    )
                                }
                                Text(
                                    text = "Tài khoản",
                                )
                            }

                        }
                    }
                }

            }
        ) {
            if (isLoading) {
                Text(text = "Đang tải dữ liệu...")
            } else if (errorMessage != null) {
                Text(text = "Lỗi: $errorMessage")
            } else {
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(it)) {
                    // LazyRow cho Laptop Văn Phòng
                    item {
                        Row {
                            Text(
                                text = "Laptop Văn Phòng",
                                modifier = Modifier.padding(8.dp),
                                fontWeight = FontWeight.Bold,
                            )

                        }
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(danhSachSanPhamVanPhong) { sanpham ->
                                ProductCard(sanpham, navController)
                            }
                        }
                    }


                    // LazyRow cho Laptop Gaming
                    item {
                        Text(
                            text = "Laptop Gaming",
                            modifier = Modifier.padding(8.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(danhSachSanPhamGaming) { sanpham ->
                                ProductCard(sanpham, navController)
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Tất cả sản phẩm",
                            modifier = Modifier.padding(8.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(danhSachSanPham) { sanpham ->
                                ProductCard(sanpham, navController)
                            }
                        }
                    }
                }
            }
        }
    }

}


