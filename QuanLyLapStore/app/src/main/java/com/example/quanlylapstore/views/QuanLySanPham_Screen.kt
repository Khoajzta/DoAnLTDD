package com.example.quanlylapstore.views

import ProductCard
import SanPhamViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuanLySanPhamScreen(navController: NavHostController,viewModel:SanPhamViewModel){

    val allSanPham by viewModel.allSanPham.observeAsState(emptyList())
    val allSanPhamGaming by viewModel.allSanPhamGaming.observeAsState(emptyList())
    val allSanPhamVanPhong by viewModel.allSanPhamVanPhong.observeAsState(emptyList())

    Scaffold (

        topBar = {
            TopAppBar(
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
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                ),
                title = {
                    Box (
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            "QUẢN LÝ SẢN PHẨM",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            )
        }
    ){
        LazyColumn(modifier = Modifier.fillMaxSize().padding(it)) {
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
                ) {
                    items(allSanPhamVanPhong) { sanpham ->
                        ProductCard(
                            sanpham,
                            {
                                viewModel.deleteSanPham(
                                    id = sanpham.MaSanPham
                                )
                            }
                        )

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
                    items(allSanPhamGaming) { sanpham ->
                        ProductCard(
                            sanpham,
                            {
                                viewModel.deleteSanPham(
                                    id = sanpham.MaSanPham
                                )
                            }
                        )
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
                    items(allSanPham) { sanpham ->
                        ProductCard(
                            sanpham,
                            {
                                viewModel.deleteSanPham(
                                    id = sanpham.MaSanPham
                                )
                            }
                        )
                    }
                }
            }

        }
    }
}