package com.example.lapstore.views

import NavRoute
import ProductCard
import SanPhamViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.example.lapstore.models.SanPham

@Composable
fun HomeScreen(navController: NavHostController, viewModel: SanPhamViewModel) {

    val danhSachSanPham = viewModel.danhSachAllSanPham
    val danhSachSanPhamGaming = viewModel.danhSachSanPhamGaming
    val danhSachSanPhamVanPhong = viewModel.danhSachSanPhamVanPhong
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    LaunchedEffect(Unit) {
        viewModel.getSanPhamTheoLoai(maLoaiSanPham = 2, isLoai1 = true)  // Lấy danh sách loại 1
        viewModel.getSanPhamTheoLoai(maLoaiSanPham = 1, isLoai1 = false) // Lấy danh sách loại 2
        viewModel.getAllSanPham()
    }

    if (isLoading) {
        Text(text = "Đang tải dữ liệu...")
    } else if (errorMessage != null) {
        Text(text = "Lỗi: $errorMessage")
    } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
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
                            ProductCard(sanpham,navController)
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
                            ProductCard(sanpham,navController)
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
                            ProductCard(sanpham,navController)
                        }
                    }
                }
            }

        }

}


