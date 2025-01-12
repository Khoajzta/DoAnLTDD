package com.example.lapstore.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.LaunchedEffect
import com.example.lapstore.viewmodels.DiaChiViewmodel


@Composable
fun AddressManagementScreen(diaChiViewModel: DiaChiViewmodel) {
//    // Trạng thái để quản lý loại màn hình đang hiển thị
//    var currentScreen by remember { mutableStateOf("list") }
//    var selectedAddress by remember { mutableStateOf<Triple<String, String, String>?>(null) } // Lưu tên và địa chỉ
//
//    // Gọi API để lấy danh sách địa chỉ
//    val MaKhachHang = 1 // Ví dụ, bạn có thể lấy từ thông tin người dùng đăng nhập
//    LaunchedEffect(MaKhachHang) {
//        diaChiViewModel.getDiaChiByKhachHang(MaKhachHang)
//    }
//
//    // Kiểm tra kết quả từ ViewModel khi có sự thay đổi
//    LaunchedEffect(diaChiViewModel.diachiUpdateResult) {
//        // Khi có sự thay đổi trong diachiUpdateResult, bạn có thể refresh lại danh sách địa chỉ
//        if (diaChiViewModel.diachiUpdateResult.contains("Thêm địa chỉ thành công")) {
//            // Cập nhật lại danh sách địa chỉ
//            diaChiViewModel.getDiaChiByKhachHang(MaKhachHang)
//        }
//    }
//    when (currentScreen) {
//        "list" -> {
//            // Hiển thị danh sách địa chỉ
//            AddressesSection(
//                addresses = diaChiViewModel.listDiaChi, // Sử dụng dữ liệu từ ViewModel
//                onAddNewAddressClick = { currentScreen = "add" },
//                onEditAddressClick = { addressName, addressPhone, addressDetails ->
//                    selectedAddress = Triple(addressName, addressPhone, addressDetails)
//                    currentScreen = "edit"
//                }
//            )
//        }
//        "add" -> {
//            // Hiển thị màn hình thêm địa chỉ
//            AddNewAddressScreen(
//                onBackClick = { currentScreen = "list" },
//                onSaveClick = { name, phone, address ->
//                    diaChiViewModel.createDiaChi(DiaChi(Name = name, SoDienThoai = phone, ThongTinDiaChi = address))
//                    currentScreen = "list"
//                }
//            )
//        }
//        "edit" -> {
//            // Hiển thị màn hình chỉnh sửa địa chỉ
//            EditAddressScreen(
//                addressName = selectedAddress?.first ?: "",
//                addressDetails = selectedAddress?.third ?: "",
//                addressPhone = selectedAddress?.second ?: "",
//                onBackClick = { currentScreen = "list" }
//            )
//        }
//    }
}


//@Composable
//fun AddressesSection(
//    addresses: List<DiaChi>,
//    onAddNewAddressClick: () -> Unit,
//    onEditAddressClick: (String, String, String) -> Unit
//) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
//        modifier = Modifier.fillMaxWidth(),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text("Địa chỉ của bạn", fontWeight = FontWeight.Bold, fontSize = 20.sp)
//            AddAddressButton(onClick = onAddNewAddressClick)
//            Spacer(modifier = Modifier.height(16.dp))
//            // Danh sách địa chỉ
//            addresses.forEach { address ->
//                AddressItem(
//                    name = address.Name,
//                    phone = address.SoDienThoai,
//                    address = address.ThongTinDiaChi,
//                    isDefault = false, // Cần xử lý thêm logic mặc định nếu cần
//                    onEditClick = {
//                        onEditAddressClick(address.Name, address.SoDienThoai, address.ThongTinDiaChi)
//                    }
//                )
//                Divider(modifier = Modifier.padding(vertical = 8.dp))
//            }
//        }
//    }
//}
//
//@Composable
//fun AddNewAddressScreen(onBackClick: () -> Unit, onSaveClick: (String, String, String) -> Unit) {
//    var name by remember { mutableStateOf("") }
//    var phone by remember { mutableStateOf("") }
//    var address by remember { mutableStateOf("") }
//    var checked by remember { mutableStateOf(true) }
//
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
//        modifier = Modifier.fillMaxWidth(),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            // Tiêu đề và nút quay lại
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                IconButton(onClick = onBackClick) {
//                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                }
//                Spacer(modifier = Modifier.width(6.dp))
//                Text("Thêm địa chỉ mới", fontWeight = FontWeight.Bold, fontSize = 20.sp)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Các trường thông tin địa chỉ
//            Text("Thông tin liên hệ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
//            Spacer(modifier = Modifier.height(6.dp))
//
//            OutlinedTextField(
//                modifier = Modifier.fillMaxWidth(),
//                value = name,
//                label = { Text("Họ và tên") },
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color.Red,
//                    unfocusedBorderColor = Color.Red,
//                    focusedLabelColor = Color.Red
//                ),
//                shape = RoundedCornerShape(17.dp),
//                onValueChange = { name = it }
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            OutlinedTextField(
//                modifier = Modifier.fillMaxWidth(),
//                value = phone,
//                label = { Text("Số điện thoại") },
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color.Red,
//                    unfocusedBorderColor = Color.Red,
//                    focusedLabelColor = Color.Red
//                ),
//                shape = RoundedCornerShape(17.dp),
//                onValueChange = { phone = it }
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text("Thông tin địa chỉ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
//            Spacer(modifier = Modifier.height(6.dp))
//
//            OutlinedTextField(
//                modifier = Modifier.fillMaxWidth(),
//                value = address,
//                label = { Text("Thông tin địa chỉ cụ thể") },
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color.Red,
//                    unfocusedBorderColor = Color.Red,
//                    focusedLabelColor = Color.Red
//                ),
//                shape = RoundedCornerShape(17.dp),
//                onValueChange = { address = it }
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text("Cài đặt", fontWeight = FontWeight.Bold, fontSize = 16.sp)
//            Spacer(modifier = Modifier.height(6.dp))
//
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(text = "Đặt làm mặt định", modifier = Modifier.weight(1f))
//                Switch(
//                    checked = checked,
//                    onCheckedChange = { checked = it },
//                    thumbContent = {
//                        if (checked) {
//                            Icon(imageVector = Icons.Filled.Check, contentDescription = "")
//                        } else {
//                            Icon(imageVector = Icons.Filled.Close, contentDescription = "")
//                        }
//                    },
//                    colors = SwitchDefaults.colors(
//                        checkedThumbColor = Color.Red,
//                        uncheckedThumbColor = Color.White,
//                        checkedTrackColor = Color.LightGray,
//                        uncheckedTrackColor = Color.LightGray
//                    )
//                )
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Nút lưu địa chỉ
//            Button(
//                onClick = { onSaveClick(name, phone, address) },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(8.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
//            ) {
//                Text("Lưu", color = Color.White, fontSize = 16.sp)
//            }
//        }
//    }
//}
//
//
//@Composable
//fun AddressItem(
//    name: String,
//    phone: String,
//    address: String,
//    isDefault: Boolean,
//    onEditClick: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onEditClick() } // Chuyển sang trang chỉnh sửa
//    ) {
//        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
//        Text(text = phone, fontSize = 14.sp, color = Color.Gray)
//        Text(text = address, fontSize = 14.sp, color = Color.Gray)
//        if (isDefault) {
//            Text(
//                text = "Mặc định",
//                fontSize = 14.sp,
//                color = Color.Red,
//                modifier = Modifier.padding(vertical = 4.dp)
//            )
//        }
//    }
//}
//
//
//@Composable
//fun AddAddressButton(onClick: () -> Unit) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        IconButton(onClick = onClick) {
//            Icon(Icons.Default.AddLocation, contentDescription = "Add Address")
//        }
//        Text(text = "Thêm địa chỉ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
//    }
//}
//
//@Composable
//fun EditAddressScreen(addressName: String, addressPhone: String, addressDetails: String, onBackClick: () -> Unit) {
//    var checked by remember { mutableStateOf(true) }
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
//        modifier = Modifier.fillMaxWidth(),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                IconButton(onClick = onBackClick) {
//                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                }
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Chỉnh sửa địa chỉ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Họ và tên: $addressName", fontWeight = FontWeight.Bold, fontSize = 16.sp)
//            Text("Số điện thoại: $addressPhone", fontSize = 14.sp, color = Color.Gray)
//            Text("Địa chỉ: $addressDetails", fontSize = 14.sp, color = Color.Gray)
//            Spacer(modifier = Modifier.height(16.dp))
//            // Thêm các trường chỉnh sửa ở đây
//            OutlinedTextField(
//                value = addressName,
//                onValueChange = {},
//                label = { Text("Họ và tên") },
//                modifier = Modifier.fillMaxWidth(),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color.Red,
//                    unfocusedBorderColor = Color.Red,
//                    focusedLabelColor = Color.Red
//                ),
//                shape = RoundedCornerShape(17.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            OutlinedTextField(
//                value = addressPhone,
//                onValueChange = {},
//                label = { Text("Số điện thoại") },
//                modifier = Modifier.fillMaxWidth(),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color.Red,
//                    unfocusedBorderColor = Color.Red,
//                    focusedLabelColor = Color.Red
//                ),
//                shape = RoundedCornerShape(17.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            OutlinedTextField(
//                value = addressDetails,
//                onValueChange = {},
//                label = { Text("Địa chỉ cụ thể") },
//                modifier = Modifier.fillMaxWidth(),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = Color.Red,
//                    unfocusedBorderColor = Color.Red,
//                    focusedLabelColor = Color.Red
//                ),
//                shape = RoundedCornerShape(17.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(text = "Đặt làm mặt định ", modifier = Modifier.weight(1f))
//                Switch(checked = checked,
//                    onCheckedChange = { checked = it },
//                    thumbContent = {
//                        if (checked) {
//                            Icon(
//                                imageVector = Icons.Filled.Check,
//                                contentDescription = ""
//                            )
//                        } else {
//                            Icon(
//                                imageVector = Icons.Filled.Close,
//                                contentDescription = ""
//                            )
//                        }
//                    },
//                    colors = SwitchDefaults.colors(
//                        checkedThumbColor = Color.Red,
//                        uncheckedThumbColor = Color.White,
//                        checkedTrackColor = Color.LightGray,
//                        uncheckedTrackColor = Color.LightGray
//                    )
//                )
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = { /* Lưu thông tin chỉnh sửa */ },
//                modifier = Modifier.fillMaxWidth(),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
//            ) {
//                Text("Lưu", color = Color.White)
//            }
//        }
//    }
//}




