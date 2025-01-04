package com.example.quanlylapstore.views

import SanPhamViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.quanlylapstore.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                ),
                title = {
                    Box (
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            "QUẢN LÝ CỬA HÀNG LAPSTORE",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            )
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {
                        navController.navigate(NavRoute.QUANLYSANPHAM.route)
                    }
                ) {
                    Text(
                        "Quản Lý Sản Phẩm",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {}
                ) {
                    Text(
                        "Quản Lý Đơn Hàng",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {}
                ) {
                    Text(
                        "Quản Lý Card Màn Hình",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {}
                ) {
                    Text(
                        "Quản Lý Hãng sản xuất",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {}
                ) {
                    Text(
                        "Quản Lý Loại Sản Phẩm",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {}
                ) {
                    Text(
                        "Quản Lý Loại Màn Hình",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {}
                ) {
                    Text(
                        "Quản Lý Loại Màu Sắc",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {}
                ) {
                    Text(
                        "Quản Lý Nhà Cung Cấp",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {}
                ) {
                    Text(
                        "Quản Lý RAM",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {}
                ) {
                    Text(
                        "Quản Lý ROM",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth().size(height = 50.dp, width = 200.dp).padding(bottom = 10.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    onClick = {}
                ) {
                    Text(
                        "Quản Lý Tài Khoản",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}