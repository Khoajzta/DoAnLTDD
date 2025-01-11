package com.example.lapstore.views

import ChangePasswordSection
import NavRoute
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lapstore.models.KhachHang
import com.example.lapstore.viewmodels.KhachHangViewModel
import com.example.lapstore.viewmodels.TaiKhoanViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcccountScreen(
    navController: NavHostController,
    tentaikhoan: String
) {
    var currentTab by remember { mutableStateOf("accountInfo") }

    Scaffold(
        containerColor = Color.Red,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Red
                ),
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
                title = {
                    Text(
                        "TÀI KHOẢN",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

            )

        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFFF2F2F2))
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    when (currentTab) {
                        "accountInfo" -> AccountInfoSection(tentaikhoan)
                        "cartManagement" -> CartManagementSection()
                        "changePassword" -> ChangePasswordSection(tentaikhoan)
                        "addresses" -> AddressesSection()
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Phần menu danh sách bên dưới
            item {
                AccountOptionsSection(
                    onOptionSelected = { selectedTab ->
                    currentTab = selectedTab
                }, currentTab = currentTab,
                    navController
                )
            }
        }
    }


}

@Composable
fun AccountInfoSection(
    tentaikhoan: String
) {
    var taikhoanviewModel:TaiKhoanViewModel = viewModel()
    var khachhangviewModel:KhachHangViewModel = viewModel()

    val taikhoan = taikhoanviewModel.taikhoan
    val khachhang = khachhangviewModel.khachhang

    LaunchedEffect(tentaikhoan) {
        if (tentaikhoan.isNotEmpty()) {
            taikhoanviewModel.getSanTaiKhoanByTentaikhoan(tentaikhoan)
        }
    }

    if (taikhoan!=null) {
        khachhangviewModel.getKhachHangById(taikhoan.MaKhachHang.toString())
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Thông tin tài khoản", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(8.dp))
            if(khachhang!=null){
                val hoTen = remember { mutableStateOf(khachhang.HoTen) }
                val soDienThoai = remember { mutableStateOf(khachhang.SoDienThoai) }
                val email = remember { mutableStateOf(khachhang.Email) }
                val gioiTinh = remember { mutableStateOf(khachhang.GioiTinh) }
                val selectedDay = remember { mutableStateOf(khachhang.NgaySinh.split("-")[2]) }
                val selectedMonth = remember { mutableStateOf(khachhang.NgaySinh.split("-")[1]) }
                val selectedYear = remember { mutableStateOf(khachhang.NgaySinh.split("-")[0]) }

                Text("Họ Tên: ", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = khachhang.HoTen,
                    onValueChange = {khachhang.HoTen = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Red,
                        focusedLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(17.dp),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Giới tính: ", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        listOf("Nam", "Nữ").forEach { gender ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = gioiTinh.value == gender,
                                    onClick = { gioiTinh.value = gender },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color.Red
                                    )

                                )
                                Text(text = gender)
                            }
                        }
                    }
                }

                // Số điện thoại
                Text("Số điện thoại: ", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = khachhang.SoDienThoai,
                    onValueChange = { khachhang.SoDienThoai = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Red,
                        focusedLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(17.dp),
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Email
                Text("Email: ", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = khachhang.Email,
                    onValueChange = { khachhang.Email = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Red,
                        unfocusedBorderColor = Color.Red,
                        focusedLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(17.dp),
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DropdownMenuField(
                        label = "Ngày",
                        items = (1..31).map { it.toString() },
                        selectedValue = selectedDay.value,
                        onValueChange = { selectedDay.value = it },
                        modifier = Modifier
                            .weight(1.15f)
                            .padding(end = 0.5.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    DropdownMenuField(
                        label = "Tháng",
                        items = (1..12).map { it.toString() },
                        selectedValue = selectedMonth.value,
                        onValueChange = { selectedMonth.value = it },
                        modifier = Modifier
                            .weight(1.2f)
                            .padding(horizontal = 0.5.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    DropdownMenuField(
                        label = "Năm",
                        items = (1900..2025).map { it.toString() }.reversed(),
                        selectedValue = selectedYear.value,
                        onValueChange = { selectedYear.value = it },
                        modifier = Modifier
                            .weight(1.4f)
                            .padding(start = 0.5.dp)
                    )
                }
            }


            // Họ tên


            Spacer(modifier = Modifier.height(16.dp))

            // Nút "Lưu thay đổi"
            Button(
                onClick = {
//                    // Cập nhật thông tin mới vào đối tượng KhachHang
//                    val updatedKhachHang = khachHang.copy(
//                        HoTen = hoTen.value,
//                        SoDienThoai = soDienThoai.value,
//                        Email = email.value,
//                        GioiTinh = gioiTinh.value,
//                        NgaySinh = "${selectedYear.value}-${selectedMonth.value}-${selectedDay.value}"
//                    )
//                    // Sau đó có thể gọi API hoặc thực hiện hành động lưu ở đây
//                    println("Thông tin đã được lưu: $updatedKhachHang")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("LƯU THAY ĐỔI", color = Color.White)
            }
        }
    }

}


@Composable
fun DropdownMenuField(
    label: String,
    items: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Red
) {
    var isExpanded by remember { mutableStateOf(false) } // Trạng thái menu

    Column(modifier = modifier) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = { onValueChange(it) },
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                Text(
                    text = "▼",
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .padding(8.dp)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Red,
                focusedLabelColor = Color.Red
            ),
            shape = RoundedCornerShape(17.dp),
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .height(400.dp)
                .widthIn(300.dp)
                .align(Alignment.CenterHorizontally), // Căn giữa DropdownMenu
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onValueChange(item)
                        isExpanded = false
                    }
                )
            }
        }


    }
}


@Composable
fun AccountOptionsSection(
    onOptionSelected: (String) -> Unit,
    currentTab: String,
    navController:NavHostController
) {
    val openDialog = remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AccountOptionItem(
                iconRes = Icons.Filled.Person,
                label = "Thông tin tài khoản",
                isSelected = currentTab == "accountInfo",
                onClick = { onOptionSelected("accountInfo") }
            )
            AccountOptionItem(
                iconRes = Icons.Filled.LocationOn,
                label = "Số địa chỉ",
                isSelected = currentTab == "addresses",
                onClick = { onOptionSelected("addresses") }
            )
            AccountOptionItem(
                iconRes = Icons.Filled.ShoppingCart,
                label = "Quản lý đơn hàng",
                isSelected = currentTab == "cartManagement",
                onClick = { onOptionSelected("cartManagement") }
            )
            AccountOptionItem(
                iconRes = Icons.Filled.Lock,
                label = "Đổi mật khẩu",
                isSelected = currentTab == "changePassword",
                onClick = { onOptionSelected("changePassword") }
            )
            AccountOptionItem(
                iconRes = Icons.Filled.ExitToApp,
                label = "Đăng xuất",
                isSelected = false, // Không cần trạng thái cho mục đăng xuất
                onClick = {
                    openDialog.value =true
                }
            )
        }
    }

    if (openDialog.value ==true) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = { openDialog.value = false },
            title = { Text("Đăng xuất") },
            text = { Text("Đăng xuất tài khoản của bạn?", fontSize = 17.sp) },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        navController.navigate(NavRoute.HOME.route)
                    }
                ) {
                    Text("OK", color = Color.Red,fontSize = 14.sp)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Hủy", color = Color.Black,fontSize = 14.sp)
                }
            }
        )
    }
}

@Composable
fun AccountOptionItem(
    iconRes: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = iconRes,
            contentDescription = label,
            tint = if (isSelected) Color.Red else Color.Gray
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            color = if (isSelected) Color.Red else Color.Black,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun CartManagementSection() {
    Text("Đây là trang Quản lý đơn hàng", fontWeight = FontWeight.Bold)
}


@Composable
fun AddressesSection() {
    Text("Đây là trang Số địa chỉ", fontWeight = FontWeight.Bold)
}
