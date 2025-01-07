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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.lapstore.models.SanPham


@Composable
fun ProductDetail_Screen(
    navController: NavHostController,
    id: String,
    viewModel: SanPhamViewModel,
    hinhAnhViewModel: HinhAnhViewModel
) {
    val danhSachHinhAnh = hinhAnhViewModel.danhsachhinhanhtheosanpham
    val sanPham = viewModel.sanPham

    var hinhAnhHienTai by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(id) {
        if (id.isNotEmpty()) {
            viewModel.getSanPhamById(id)
            hinhAnhViewModel.getHinhAnhTheoSanPham(id)
        }
    }

    LaunchedEffect(danhSachHinhAnh) {
        if (danhSachHinhAnh.isNotEmpty()) {
            hinhAnhHienTai = danhSachHinhAnh.first().DuongDan
        }
    }

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
                    onClick = { /* TODO: Xử lý thêm vào giỏ hàng */ },
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


fun formatGiaTien(gia: Int): String {
    val formatter = DecimalFormat("#,###")
    return "${formatter.format(gia)}đ"
}





