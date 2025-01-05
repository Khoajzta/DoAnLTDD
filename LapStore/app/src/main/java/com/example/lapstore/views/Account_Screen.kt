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
    val khachHangList by khachHangViewModel.allKhachHang.observeAsState(emptyList())
    val currentKhachHang = if (khachHangList.isNotEmpty()) khachHangList[0] else null

    var currentTab by remember { mutableStateOf("accountInfo") } // Tab hiện tại

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ) {
                when (currentTab) {
                    "accountInfo" -> AccountInfoSection(currentKhachHang)
                    "cartManagement" -> CartManagementSection()
                    "changePassword" -> ChangePasswordSection()
                    "addresses" -> AddressesSection()
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Phần menu danh sách bên dưới
        item {
            AccountOptionsSection(onOptionSelected = { selectedTab ->
                currentTab = selectedTab
            }, currentTab = currentTab)
        }
    }
}

@Composable
fun AccountInfoSection(currentKhachHang: KhachHang?) {
    var selectedDay by remember { mutableStateOf("1") }
    var selectedMonth by remember { mutableStateOf("1") }
    var selectedYear by remember { mutableStateOf("2000") }
    val lsGenders = mutableListOf("Nam", "Nữ")
    var selection by remember { mutableStateOf(lsGenders[0]) }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Thông tin tài khoản", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Họ Tên", fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = currentKhachHang?.HoTen ?: "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Giới tính:", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    lsGenders.forEach { gender ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(selected = selection == gender,
                                onClick = { selection = gender })
                            Text(text = gender)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Số điện thoại", fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = currentKhachHang?.SoDienThoai ?: "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )

            Text("Email", fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = currentKhachHang?.Email ?: "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Ngày sinh", fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Dropdown cho ngày
                DropdownMenuField(
                    label = "Ngày",
                    items = (1..31).map { it.toString() },
                    selectedValue = selectedDay,
                    onValueChange = { selectedDay = it },
                    modifier = Modifier.weight(1.15f).padding(end = 0.5.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))

                // Dropdown cho tháng
                DropdownMenuField(
                    label = "Tháng",
                    items = (1..12).map { it.toString() },
                    selectedValue = selectedMonth,
                    onValueChange = { selectedMonth = it },
                    modifier = Modifier.weight(1.2f).padding(horizontal = 0.5.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))

                // Dropdown cho năm giảm dần
                DropdownMenuField(
                    label = "Năm",
                    items = (1900..2025).map { it.toString() }.reversed(),
                    selectedValue = selectedYear,
                    onValueChange = { selectedYear = it },
                    modifier = Modifier.weight(1.4f).padding(start = 0.5.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Lưu thay đổi */ },
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
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) } // Trạng thái mở menu

    Column(modifier = modifier.padding(8.dp)) {
        // OutlinedTextField với nút xổ xuống
        OutlinedTextField(
            value = selectedValue,
            onValueChange = { onValueChange(it) }, // Cập nhật giá trị khi nhập
            label = { Text(label) },
            readOnly = false, // Cho phép nhập trực tiếp
            trailingIcon = { // Biểu tượng xổ xuống
                Text(
                    text = "▼",
                    modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .padding(8.dp)
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        // DropdownMenu
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 300.dp) // Giới hạn chiều cao menu
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onValueChange(item) // Cập nhật giá trị khi chọn
                        isExpanded = false // Đóng menu
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
