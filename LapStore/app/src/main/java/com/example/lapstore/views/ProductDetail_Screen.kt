package com.example.lapstore.views

import SanPhamViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
) {
    val sanPham = viewModel.sanPham

    LaunchedEffect(id) {
        viewModel.getSanPhamById(id)
    }

    // Kiểm tra trạng thái và hiển thị giao diện
    if (sanPham == null) {
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Hình ảnh sản phẩm
            item {
                AsyncImage(
                    model = sanPham.HinhAnh,
                    contentDescription = "Hình ảnh sản phẩm",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            // Tên sản phẩm
            item {
                Text(
                    text = sanPham!!.TenSanPham,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            // Giá sản phẩm
            item {
                Text(
                    text = "Giá: ${sanPham!!.Gia}đ",
                    fontSize = 20.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
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
                    Text("CPU: ${sanPham!!.MaCPU}")
                    Text("Card đồ họa: ${sanPham!!.MaCardDoHoa}")
                    Text("RAM: ${sanPham!!.MaRAM} GB")
                    Text("ROM: ${sanPham!!.MaROM} GB")
                }
            }

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
                    Text("CPU: ${sanPham!!.MaCPU}")
                    Text("Card đồ họa: ${sanPham!!.MaCardDoHoa}")
                    Text("RAM: ${sanPham!!.MaRAM} GB")
                    Text("ROM: ${sanPham!!.MaROM} GB")
                }
            }

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
                    Text("CPU: ${sanPham!!.MaCPU}")
                    Text("Card đồ họa: ${sanPham!!.MaCardDoHoa}")
                    Text("RAM: ${sanPham!!.MaRAM} GB")
                    Text("ROM: ${sanPham!!.MaROM} GB")
                }
            }
        }
    }

}


