package com.example.tapdieuhuong

import HinhAnhViewModel
import SanPhamViewModel
import android.annotation.SuppressLint


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.lapstore.R
import com.example.lapstore.models.HinhAnh
import com.example.lapstore.models.SanPham


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
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TapDieuHuong() {
    var selectedTabIndexItem by remember { mutableStateOf(0) }
    val tabs = listOf("Đơn hàng", "Chưa Thanh Toán", "Chờ vận chuyển", "Đang vận chuyển", "Đang giao", "Đã giao")

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
                0 -> DonHangScreen(modifier = Modifier.padding(padding), sanPhamViewModel = SanPhamViewModel(),
                    hinhAnhViewModel = HinhAnhViewModel())
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
fun DonHangScreen(modifier: Modifier = Modifier,
                  sanPhamViewModel: SanPhamViewModel,
                  hinhAnhViewModel: HinhAnhViewModel

) {
    sanPhamViewModel.getAllSanPham()
    var sanPhamList: List<SanPham> = sanPhamViewModel.danhSachAllSanPham
    // Danh sách trạng thái checkbox cho từng sản phẩm
    val sanPhamStates = mutableStateListOf<Boolean>().apply {
        repeat(sanPhamList.size) { add(false) }
    }
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
            items(sanPhamList.size) { index ->
                val sanPham = sanPhamList[index]
                SanPhamCard(
                    isChecked = sanPhamStates[index],
                    sanPham = sanPham,
                    onCheckedChange = { isChecked ->
                        sanPhamStates[index] = isChecked
                    },
                    onClick = {
                        // Xử lý khi nhấn vào card sản phẩm
//                        navController.navigate()
                        println("Sản phẩm được chọn: ${sanPham.TenSanPham}")
                    }
                )
            }
        }

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
    sanPham: SanPham,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
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
          AsyncImage(
              model = sanPham.HinhAnh,
              contentDescription = null,
              modifier = Modifier.padding(8.dp).size(80.dp),
              contentScale = ContentScale.Fit
          )

            Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa ảnh và nội dung

            // Nội dung văn bản và nút
            Column(modifier = Modifier.weight(1f)) { // Chiếm không gian còn lại
                Text(text = "${sanPham.TenSanPham}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Mô tả quà tặng", style = MaterialTheme.typography.bodySmall)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.padding(4.dp)) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            Text(text = "Giá:${sanPham.Gia} ", style = MaterialTheme.typography.bodySmall)
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
                                onClick = {
                                    sanPham.SoLuong+=1;
                                },
                                modifier = Modifier.size(32.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                contentPadding = PaddingValues(0.dp),
                                shape = RectangleShape
                            ) {
                                Text("+")
                            }
                            Text(
                                text = "${sanPham.Gia}",
                                modifier = Modifier.padding(horizontal = 8.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Button(
                                onClick = {
                                    sanPham.SoLuong-=1;
                                },
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