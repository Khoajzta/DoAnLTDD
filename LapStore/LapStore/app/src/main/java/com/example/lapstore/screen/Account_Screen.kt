import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreenUI() {
    var currentTab by remember { mutableStateOf("accountInfo") } // Tab hiện tại

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
    ) {
        // Phần tiêu đề
        TopAppBar(
            title = {
                Text(
                    text = when (currentTab) {
                        "accountInfo" -> "Thông tin tài khoản"
                        "cartManagement" -> "Quản lý đơn hàng"
                        "viewedProducts" -> "Sản phẩm đã xem"
                        "addresses" -> "Số địa chỉ"
                        else -> "Thông tin tài khoản"
                    },
                    color = Color.Black
                )
            },
            modifier = Modifier.background(Color.Red)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nội dung chính của từng tab
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            when (currentTab) {
                "accountInfo" -> AccountInfoSection()
                "cartManagement" -> CartManagementSection()
                "viewedProducts" -> ViewedProductsSection()
                "addresses" -> AddressesSection()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Phần menu danh sách bên dưới
        AccountOptionsSection(onOptionSelected = { selectedTab ->
            currentTab = selectedTab
        }, currentTab = currentTab)
    }
}

@Composable
fun AccountInfoSection() {
    var selectedDay by remember { mutableStateOf("1") }
    var selectedMonth by remember { mutableStateOf("1") }
    var selectedYear by remember { mutableStateOf("2000") }
    val lsGenders = mutableListOf("Nam", "Nữ")
    var selection by remember {
        mutableStateOf(lsGenders[0])}
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Họ Tên", fontWeight = FontWeight.Bold)
            OutlinedTextField(value = "",
                onValueChange = {}, modifier = Modifier.fillMaxWidth(), readOnly = false)

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
            Row {
                OutlinedTextField(value = "",
                    onValueChange ={}, Modifier.fillMaxWidth(), readOnly = false,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
            }

            Text("Email", fontWeight = FontWeight.Bold)
            Row {
                OutlinedTextField("",
                    onValueChange = {}, Modifier.fillMaxWidth(), readOnly = false)
            }

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
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp).fillMaxWidth()
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
                iconRes = Icons.Filled.Visibility,
                label = "Sản phẩm đã xem",
                isSelected = currentTab == "viewedProducts",
                onClick = { onOptionSelected("viewedProducts") }
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
fun ViewedProductsSection() {
    Text("Đây là trang Sản phẩm đã xem", fontWeight = FontWeight.Bold)
}

@Composable
fun AddressesSection() {
    Text("Đây là trang Số địa chỉ", fontWeight = FontWeight.Bold)
}