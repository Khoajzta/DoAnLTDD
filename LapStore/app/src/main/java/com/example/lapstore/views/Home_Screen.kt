package com.example.lapstore.views

import NavRoute
import ProductCard
import SanPhamAPIService
import SanPhamViewModel
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import android.icu.text.NumberFormat
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
>>>>>>> Stashed changes
=======
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
>>>>>>> main
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
=======
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
>>>>>>> main
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
<<<<<<< HEAD
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
=======
>>>>>>> main
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
<<<<<<< HEAD
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
=======
>>>>>>> main
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
<<<<<<< HEAD
import androidx.compose.material3.Surface
>>>>>>> Stashed changes
=======
>>>>>>> main
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
<<<<<<< HEAD
<<<<<<< Updated upstream
import androidx.compose.ui.Modifier
=======
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
=======
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
>>>>>>> main
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
<<<<<<< HEAD
>>>>>>> Stashed changes
=======
>>>>>>> main
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import coil.compose.AsyncImage
import com.example.lapstore.CategoryMenu
import com.example.lapstore.api.QuanLyBanLaptopRetrofitClient
>>>>>>> Stashed changes
=======
import com.example.lapstore.CategoryMenu
>>>>>>> main
import com.example.lapstore.models.SanPham
import com.example.lapstore.models.TaiKhoan
import com.example.lapstore.viewmodels.TaiKhoanViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

<<<<<<< HEAD
<<<<<<< Updated upstream
=======


@OptIn(ExperimentalMaterial3Api::class)
>>>>>>> Stashed changes
@Composable
fun HomeScreen(navController: NavHostController, viewModel: SanPhamViewModel) {
    val allSanPham by viewModel.allSanPham.observeAsState(emptyList())
    val allSanPhamGaming by viewModel.allSanPhamGaming.observeAsState(emptyList())
    val allSanPhamVanPhong by viewModel.allSanPhamVanPhong.observeAsState(emptyList())

<<<<<<< Updated upstream
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        // LazyRow cho Laptop Văn Phòng
        item {
            Text(
                text = "Laptop Văn Phòng",
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
=======
    val danhSachSanPham = viewModel.danhSachAllSanPham
    val danhSachSanPhamGaming = viewModel.danhSachSanPhamGaming
    val danhSachSanPhamVanPhong = viewModel.danhSachSanPhamVanPhong
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    val taiKhoanViewModel: TaiKhoanViewModel = viewModel()
    val sanPhamViewModel:SanPhamViewModel=viewModel()
    val taikhoan = taiKhoanViewModel.taikhoan
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var timkiem by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    val suggestions = viewModel.products.value
    val filteredList = danhSachSanPham.filter { it.TenSanPham.contains(searchText.toString(), ignoreCase = true) }
    if(tentaikhoan != null){
        taiKhoanViewModel.getTaiKhoanByTentaikhoan(tentaikhoan)
    }
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Red, darkIcons = false)
    }
    LaunchedEffect(Unit) {
        viewModel.getSanPhamTheoLoai(maLoaiSanPham = 1, isLoai1 = true)
        viewModel.getSanPhamTheoLoai(maLoaiSanPham = 2, isLoai1 = false)
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
>>>>>>> Stashed changes
            ) {
                items(allSanPhamVanPhong) { sanpham ->
                    ProductCard(sanpham)
                }
            }
        }
<<<<<<< Updated upstream
=======
    ){
        Scaffold(
            containerColor = Color.White,
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
                                if(taikhoan==null){
                                    navController.navigate(NavRoute.LOGINSCREEN.route)
                                }
                                else{
                                    navController.navigate("${NavRoute.CART.route}?makhachhang=${taikhoan.MaKhachHang}&tentaikhoan=${taikhoan.TenTaiKhoan}")
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

                            OutlinedTextField( value = timkiem,
                                onValueChange = { timkiem = it },
                                modifier = Modifier
                                    .height(50.dp)
                                    .fillMaxWidth()
                                    .clickable{
                                        if(timkiem==null){
                                            navController.navigate("Seach_Screen/$timkiem")
                                        }

                                        },
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
                                    IconButton(
                                        onClick = {
                                            navController.navigate(NavRoute.SEARCHSCREEN.route)
                                        },
                                        content = {
                                            Icon(
                                                imageVector = Icons.Filled.Search,
                                                contentDescription = "Search",
                                                tint = Color.Black
                                            )
                                        }
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
                                        if (tentaikhoan != null) {
                                            navController.navigate("${NavRoute.ACCOUNT.route}?tentaikhoan=${taiKhoanViewModel.tentaikhoan}")

                                        } else {
                                            navController.navigate(NavRoute.LOGINSCREEN.route)
                                        }
                                    }
                                ) {
                                    Icon(
                                        Icons.Outlined.Person,
                                        contentDescription = "Profile",
                                        tint = Color.Red
                                    )
                                }
                                Text(text = "Tài khoản")
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

                    item {
                        Text(
                            text = "Tất cả sản phẩm",
                            modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(danhSachSanPham) { sanpham ->
                                if(taikhoan!=null){
                                    ProductCard(sanpham,taikhoan.MaKhachHang.toString(), taikhoan.TenTaiKhoan, navController)
                                }
                                else{
                                    ProductCard(sanpham,null,tentaikhoan, navController)
                                }
                            }
                        }
                    }

                    // LazyRow cho Laptop Văn Phòng
                    item {
                        Row {
                            Text(
                                text = "Laptop Văn Phòng",
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                            )

                        }
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(danhSachSanPhamVanPhong) { sanphamvp ->
                                if(taikhoan!=null){
                                    ProductCard(sanphamvp,taikhoan.MaKhachHang.toString(),taikhoan.TenTaiKhoan, navController)
                                }
                                else{
                                    ProductCard(sanphamvp,null,tentaikhoan, navController)
                                }
                            }
                        }
                    }
>>>>>>> Stashed changes
=======
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: SanPhamViewModel,
    tentaikhoan:String?,
) {
    var isFocused by remember { mutableStateOf(false) }
>>>>>>> main


    val systemUiController = rememberSystemUiController()
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val danhSachSanPham = viewModel.danhSachAllSanPham
    val danhSachSanPhamGaming = viewModel.danhSachSanPhamGaming.collectAsState()
    val danhSachSanPhamVanPhong = viewModel.danhSachSanPhamVanPhong.collectAsState()
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    val taiKhoanViewModel: TaiKhoanViewModel = viewModel()

    val taikhoan = taiKhoanViewModel.taikhoan
    LaunchedEffect(isFocused) {
        if (isFocused) {
            if(taikhoan!=null)
                navController.navigate("${NavRoute.SEARCHSCREEN.route}?makhachhang=${taikhoan.MaKhachHang}&tentaikhoan=${taikhoan.TenTaiKhoan}")
            else
                navController.navigate(NavRoute.SEARCHSCREEN.route)
        }
    }

    if(tentaikhoan != null){
        taiKhoanViewModel.getTaiKhoanByTentaikhoan(tentaikhoan)
    }
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Red, darkIcons = false)
    }
    LaunchedEffect(Unit) {
        viewModel.getSanPhamTheoLoaiGaming()
        viewModel.getSanPhamTheoLoaiVanPhong()
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
                            Icons.Filled.ArrowBackIosNew,
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
            containerColor = Color.White,
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
                                if(taikhoan==null){
                                    navController.navigate(NavRoute.LOGINSCREEN.route)
                                }
                                else{
                                    navController.navigate("${NavRoute.CART.route}?makhachhang=${taikhoan.MaKhachHang}&tentaikhoan=${taikhoan.TenTaiKhoan}")
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
                                    .fillMaxWidth().onFocusChanged { focusState ->
                                        isFocused = focusState.isFocused  // Chỉ chuyển trang khi TextField được nhấn vào
                                    },
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
                                        if(taikhoan!=null){
                                            navController.navigate("${NavRoute.HOME.route}?tentaikhoan=${tentaikhoan}"){
                                                popUpTo(0) { inclusive = true }
                                            }
                                        }
                                        else{
                                            navController.navigate(NavRoute.HOME.route)
                                        }
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
                                        if (tentaikhoan != null) {
                                            navController.navigate("${NavRoute.ACCOUNT.route}?tentaikhoan=${taiKhoanViewModel.tentaikhoan}")

                                        } else {
                                            navController.navigate(NavRoute.LOGINSCREEN.route)
                                        }
                                    }
                                ) {
                                    Icon(
                                        Icons.Outlined.Person,
                                        contentDescription = "Profile",
                                        tint = Color.Red
                                    )
                                }
                                Text(text = "Tài khoản")
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

                    item {
                        Text(
                            text = "Tất cả sản phẩm",
                            modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(danhSachSanPham) { sanpham ->
                                if(taikhoan!=null){
                                    ProductCard(sanpham,taikhoan.MaKhachHang.toString(), taikhoan.TenTaiKhoan, navController)
                                }
                                else{
                                    ProductCard(sanpham,null,tentaikhoan, navController)
                                }
                            }
                        }
                    }

                    // LazyRow cho Laptop Văn Phòng
                    item {
                        Row {
                            Text(
                                text = "Laptop Văn Phòng",
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                            )

                        }
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(danhSachSanPhamVanPhong.value) { sanphamvp ->
                                if(taikhoan!=null){
                                    ProductCard(sanphamvp,taikhoan.MaKhachHang.toString(),taikhoan.TenTaiKhoan, navController)
                                }
                                else{
                                    ProductCard(sanphamvp,null,tentaikhoan, navController)
                                }
                            }
                        }
                    }


                    // LazyRow cho Laptop Gaming
                    item {
                        Text(
                            text = "Laptop Gaming",
                            modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(danhSachSanPhamGaming.value) { sanphamgm ->
                                if(taikhoan!=null){
                                    ProductCard(sanphamgm,taikhoan.MaKhachHang.toString(),tentaikhoan, navController)
                                }
                                else{
                                    ProductCard(sanphamgm,null,null, navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

