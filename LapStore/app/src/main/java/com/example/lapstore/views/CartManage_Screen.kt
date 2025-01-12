package com.example.lapstore.views

import CardDonHang
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lapstore.viewmodels.HoaDonBanVỉewModel
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartManagementSection(navController: NavHostController,makhachhang: Int?) {
    var selectedTabIndexItem by remember { mutableStateOf(0) }
    val tabs = listOf("Chờ xác nhận", "Chờ lấy hàng", "Chờ giao hàng", "Đã giao")

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Scaffold(
            containerColor = Color.White,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    ),
                    title = {
                        Text("Quản lý đơn hàng")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "")
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(3.dp),
            ) {
                ScrollableTabRow(
                    selectedTabIndex = selectedTabIndexItem,
                    edgePadding = 0.dp,
                    modifier = Modifier.fillMaxWidth(),
                    contentColor = Color.Red,
                    containerColor = Color.White,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[selectedTabIndexItem]),
                            color = Color.Red // Đặt màu đỏ cho thanh di chuyển
                        )
                    }
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
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }


                // Content
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    when (selectedTabIndexItem) {
                        0 -> ChoXacNhanScreen(makhachhang)
                    1 -> ChoLayHangScreen(makhachhang)
//                    2 -> ChoVanChuyenScreen(modifier = Modifier.fillMaxSize())
//                    3 -> DangVanChuyenScreen(modifier = Modifier.fillMaxSize())
//                    4 -> DangGiaoScreen(modifier = Modifier.fillMaxSize())
//                    5 -> DaGiaoScreen(modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}

@Composable
fun ChoLayHangScreen(makhachhang: Int?) {
    // Lấy ViewModel
    val hoaDonBanViewModel: HoaDonBanVỉewModel = viewModel()

    // Trạng thái đang tải
    val isLoading = remember { mutableStateOf(false) }
    // Trạng thái lỗi (nếu có)
    val errorMessage = remember { mutableStateOf<String?>(null) }

    Log.d("MaKhachHang", makhachhang.toString())

    // Gọi API nếu mã khách hàng không null
    if (makhachhang != null) {
        LaunchedEffect(makhachhang) {
            isLoading.value = true // Bắt đầu tải dữ liệu
            errorMessage.value = null
            try {
                hoaDonBanViewModel.getHoaDonTheoKhachHang(makhachhang, 2) // 1 là trạng thái "Chờ xác nhận"
            } catch (e: Exception) {
                errorMessage.value = "Lỗi khi tải dữ liệu: ${e.message}"
            } finally {
                isLoading.value = false // Kết thúc tải dữ liệu
            }
        }
    }

    // Lấy danh sách hóa đơn của khách hàng
    val danhSachHoaDonBan = hoaDonBanViewModel.danhSachHoaDonCuaKhachHang ?: emptyList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        when {
            isLoading.value -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            errorMessage.value != null -> {
                Text(
                    text = errorMessage.value ?: "Đã xảy ra lỗi",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

            danhSachHoaDonBan.isEmpty() -> {
                Text(
                    text = "Không có hóa đơn nào đang chờ xác nhận.",
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(4.dp)
                ) {
                    items(danhSachHoaDonBan) { hoadon ->
                        CardDonHang(hoadon)
                    }
                }
            }
        }
    }
}

@Composable
fun ChoXacNhanScreen(makhachhang: Int?) {
    // Lấy ViewModel
    val hoaDonBanViewModel: HoaDonBanVỉewModel = viewModel()

    // Trạng thái đang tải
    val isLoading = remember { mutableStateOf(false) }
    // Trạng thái lỗi (nếu có)
    val errorMessage = remember { mutableStateOf<String?>(null) }

    Log.d("MaKhachHang", makhachhang.toString())

    // Gọi API nếu mã khách hàng không null
    if (makhachhang != null) {
        LaunchedEffect(makhachhang) {
            isLoading.value = true // Bắt đầu tải dữ liệu
            errorMessage.value = null
            try {
                hoaDonBanViewModel.getHoaDonTheoKhachHang(makhachhang, 1) // 1 là trạng thái "Chờ xác nhận"
            } catch (e: Exception) {
                errorMessage.value = "Lỗi khi tải dữ liệu: ${e.message}"
            } finally {
                isLoading.value = false // Kết thúc tải dữ liệu
            }
        }
    }

    // Lấy danh sách hóa đơn của khách hàng
    val danhSachHoaDonBan = hoaDonBanViewModel.danhSachHoaDonCuaKhachHang ?: emptyList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        when {
            isLoading.value -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            errorMessage.value != null -> {
                Text(
                    text = errorMessage.value ?: "Đã xảy ra lỗi",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

            danhSachHoaDonBan.isEmpty() -> {
                Text(
                    text = "Không có hóa đơn nào đang chờ xác nhận.",
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(4.dp)
                ) {
                    items(danhSachHoaDonBan) { hoadon ->
                        CardDonHang(hoadon)
                    }
                }
            }
        }
    }
}

@Composable
fun formatDate(inputDate: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Định dạng từ API
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) // Định dạng đầu ra
        val date = inputFormat.parse(inputDate)
        date?.let { outputFormat.format(it) } ?: "Ngày không hợp lệ"
    } catch (e: Exception) {
        "Ngày không hợp lệ"
    }
}





