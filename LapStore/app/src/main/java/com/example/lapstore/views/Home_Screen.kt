package com.example.lapstore.views

import ProductCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lapstore.models.SanPham
import com.example.lapstore.viewmodels.SanPhamViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: SanPhamViewModel) {
    viewModel.getAllSanPham()
    var listSanPham: List<SanPham> = viewModel.listSanpham

    if (listSanPham.isEmpty()) {
        Text(text = "Không có sản phẩm")
    } else {

        LazyRow(
        ) {
            items(listSanPham) { sanpham ->
                ProductCard(
                    sanpham
                )
            }
        }
    }
}

