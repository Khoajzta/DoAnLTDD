package com.example.lapstore

import HinhAnhViewModel
import NavRoute
import NavgationGraph
import SanPhamViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.lapstore.ui.theme.LapStoreTheme
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavHostController
import com.example.lapstore.views.ProductItem
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import com.example.lapstore.models.SanPham

data class CategoryData(
    val title: String,
    val items: List<String>,
    val icon: ImageVector,
)

@Composable
fun CategoryMenuMain() {
    // Tạo danh sách các CategoryData đại diện cho từng CategoryMenu
    val categories = listOf(
        CategoryData("LAPTOP", listOf("Thương hiệu", "Giá bán", "CPU Intel - AMD", "Nhu cầu sử dụng"),Icons.Filled.Computer),
        CategoryData("ĐIỆN THOẠI", listOf("Hãng sản xuất", "Giá bán", "Hệ điều hành", "Dung lượng pin"),Icons.Filled.PhoneIphone),
        CategoryData("PHỤ KIỆN", listOf("Tai nghe", "Sạc dự phòng", "Cáp sạc", "Ốp lưng"),Icons.Filled.Headset)
    )

    // Sử dụng LazyColumn để hiển thị từng CategoryMenu từ danh sách
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(categories.size) { index ->
            val category = categories[index]
            CategoryMenu(title = category.title, items = category.items, icon = category.icon)
            Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách giữa các CategoryMenu
        }
    }
}

@Composable
fun CategoryMenu(title: String, items: List<String>, icon: ImageVector) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Tiêu đề danh mục (Category) với khả năng mở rộng/thu gọn
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon, // Icon đại diện (có thể thay đổi)
                contentDescription = null,
                tint = Color.Red
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null
            )
        }

        // Danh sách các mục con (Items) của danh mục
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column(modifier = Modifier.padding(start = 16.dp)) {
                items.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { /* Xử lý khi chọn từng mục con */ }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Default.ExpandMore, // Icon có thể tùy chỉnh cho từng item
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LapStoreTheme {
                val isBottomBarVisible = remember { mutableStateOf(true) }

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val viewModel = SanPhamViewModel()
                val hinhAnhViewModel = HinhAnhViewModel()
                val navController = rememberNavController()
                var searchText by remember { mutableStateOf("") }
                val systemUiController = rememberSystemUiController()
                val keyboardController = LocalSoftwareKeyboardController.current
                var searchQuery by remember { mutableStateOf("") }

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
                            CategoryMenuMain()
                            Text("Thông tin", modifier = Modifier.padding(10.dp))
                            Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                                Icon(imageVector = Icons.Outlined.SupportAgent, contentDescription = "")
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("19001009")
                            }
                        }
                    }
                ) {
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
                                            value = searchText,
                                            onValueChange = {
                                                    query ->
                                                searchQuery = query

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
                            if (isBottomBarVisible.value) { // Hiện BottomBar khi trạng thái là true
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
                                            Column (
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.SpaceAround
                                            ){
                                                IconButton(
                                                    modifier = Modifier.size(45.dp),
                                                    onClick = {
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
                                            Column (
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.SpaceAround
                                            ){
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
                                            Column (
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.SpaceAround
                                            ){
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
                                            Column (
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.SpaceAround
                                            ){
                                                IconButton(
                                                    modifier = Modifier.size(45.dp),
                                                    onClick = { navController.navigate(NavRoute.ACCOUNT.route)}
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
                        }
                    ) { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            NavgationGraph(
                                navController,
                                viewModel,
                                hinhAnhViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}





