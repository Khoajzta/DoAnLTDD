package com.example.lapstore.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lapstore.models.KhachHang
import com.example.lapstore.viewmodels.KhachHangViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcccountScreen(khachHangViewModel: KhachHangViewModel = viewModel()) {
//    val khachHangList by khachHangViewModel.allKhachHang.observeAsState(emptyList())
//    val currentKhachHang = if (khachHangList.isNotEmpty()) khachHangList[0] else null
//
//    var currentTab by remember { mutableStateOf("accountInfo") } // Tab hiện tại
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF2F2F2))
//    ) {
//        item {
//            Column(
//                modifier = Modifier
//                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
//            ) {
//                when (currentTab) {
//                    "accountInfo" -> AccountInfoSection(currentKhachHang)
//                    "cartManagement" -> CartManagementSection()
//                    "changePassword" -> ChangePasswordSection()
//                    "addresses" -> AddressesSection()
//                }
//            }
//        }
//
//        item {
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//
//        // Phần menu danh sách bên dưới
//        item {
//            AccountOptionsSection(onOptionSelected = { selectedTab ->
//                currentTab = selectedTab
//            }, currentTab = currentTab)
//        }
//    }
}

@Composable
fun AccountInfoSection(currentKhachHang: KhachHang?) {
    currentKhachHang?.let { khachHang ->
        // Dùng state để lưu trữ thông tin người dùng nhập
        val hoTen = remember { mutableStateOf(khachHang.HoTen) }
        val soDienThoai = remember { mutableStateOf(khachHang.SoDienThoai) }
        val email = remember { mutableStateOf(khachHang.Email) }
        val gioiTinh = remember { mutableStateOf(khachHang.GioiTinh) }
        val selectedDay = remember { mutableStateOf(khachHang.NgaySinh.split("-")[2]) }
        val selectedMonth = remember { mutableStateOf(khachHang.NgaySinh.split("-")[1]) }
        val selectedYear = remember { mutableStateOf(khachHang.NgaySinh.split("-")[0]) }

        // Hiển thị thông tin tài khoản và cho phép người dùng chỉnh sửa
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Thông tin tài khoản", fontWeight = FontWeight.Bold)

                // Họ tên
                Text("Họ Tên: ", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = hoTen.value,
                    onValueChange = { hoTen.value = it },
                    modifier = Modifier.fillMaxWidth()
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
                                    onClick = { gioiTinh.value = gender }
                                )
                                Text(text = gender)
                            }
                        }
                    }
                }

                // Số điện thoại
                Text("Số điện thoại: ", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = soDienThoai.value,
                    onValueChange = { soDienThoai.value = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Email
                Text("Email: ", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Ngày sinh
                Text("Ngày sinh: ", fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DropdownMenuField(
                        label = "Ngày",
                        items = (1..31).map { it.toString() },
                        selectedValue = selectedDay.value,
                        onValueChange = { selectedDay.value = it },
                        modifier = Modifier.weight(1.15f).padding(end = 0.5.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    DropdownMenuField(
                        label = "Tháng",
                        items = (1..12).map { it.toString() },
                        selectedValue = selectedMonth.value,
                        onValueChange = { selectedMonth.value = it },
                        modifier = Modifier.weight(1.2f).padding(horizontal = 0.5.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    DropdownMenuField(
                        label = "Năm",
                        items = (1900..2025).map { it.toString() }.reversed(),
                        selectedValue = selectedYear.value,
                        onValueChange = { selectedYear.value = it },
                        modifier = Modifier.weight(1.4f).padding(start = 0.5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Nút "Lưu thay đổi"
                Button(
                    onClick = {
                        // Cập nhật thông tin mới vào đối tượng KhachHang
                        val updatedKhachHang = khachHang.copy(
                            HoTen = hoTen.value,
                            SoDienThoai = soDienThoai.value,
                            Email = email.value,
                            GioiTinh = gioiTinh.value,
                            NgaySinh = "${selectedYear.value}-${selectedMonth.value}-${selectedDay.value}"
                        )
                        // Sau đó có thể gọi API hoặc thực hiện hành động lưu ở đây
                        println("Thông tin đã được lưu: $updatedKhachHang")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("LƯU THAY ĐỔI", color = Color.White)
                }
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
    modifier: Modifier = Modifier
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
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
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
fun AccountOptionsSection(onOptionSelected: (String) -> Unit, currentTab: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxWidth()
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
                    // Xử lý đăng xuất tại đây
                }
            )
        }
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
fun ChangePasswordSection() {
    Text("Đây là trang đổi mật khẩu", fontWeight = FontWeight.Bold)
}

@Composable
fun AddressesSection() {
    Text("Đây là trang Số địa chỉ", fontWeight = FontWeight.Bold)
}
