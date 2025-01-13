package com.example.lapstore.views

import DiaChiCard
import NavRoute
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lapstore.models.DiaChi
import com.example.lapstore.viewmodels.DiaChiViewmodel
import com.example.lapstore.viewmodels.KhachHangViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressManagementScreen(
    navController: NavHostController,
    makhachhang: Int?
) {
    val diaChiViewModel: DiaChiViewmodel = viewModel()


    var listDiaChi = diaChiViewModel.listDiacHi

    diaChiViewModel.getDiaChiKhachHang(makhachhang)

    Log.d("fdf",listDiaChi.toString())

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text("Quản lý địa chỉ")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Quay lại"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).padding(10.dp)
        ) {
            items(listDiaChi){diachi->
                DiaChiCard(diachi)
            }
            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        navController.navigate("${NavRoute.ADDDIACHI.route}?makhachhang=${makhachhang}")
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(imageVector = Icons.Filled.AddCircleOutline, contentDescription = "", tint = Color.White)
                        Text(
                            "Thêm địa chỉ mới",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDiaChiScreen(
    navController: NavHostController,
    makhachhang: Int?
){
    var hoten by remember { mutableStateOf("") }
    var sodienthoai by remember { mutableStateOf("") }
    var thongtindiachi by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }


    var diaChiViewmodel:DiaChiViewmodel = viewModel()

        Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Địa chỉ mới"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it).padding(10.dp).fillMaxSize(),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {
                Column(
                    modifier = Modifier.padding(7.dp)
                ) {
                    Text(
                        "Liên hệ"
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = hoten,
                        placeholder = { Text("Họ tên") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onValueChange = {
                            hoten = it
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = sodienthoai,
                        placeholder = { Text("Số điện thoại") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onValueChange = {
                            sodienthoai = it
                        }
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {
                Column(
                    modifier = Modifier.padding(7.dp)
                ) {
                    Text(
                        "Địa chỉ"
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth().height(150.dp),
                        value = thongtindiachi,
                        placeholder = { Text("Tỉnh, huyện, xã, số nhà") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                        ),
                        maxLines = 4,
                        shape = RoundedCornerShape(10.dp),
                        onValueChange = {
                            thongtindiachi = it
                        }
                    )
                }

            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {
                Column(
                    modifier = Modifier.padding(7.dp)
                ) {
                    Text(
                        "Cài đặt"
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Đặt làm mặc đinh"
                        )
                        Switch(
                            checked = isChecked,
                            colors = SwitchDefaults.colors(
                                uncheckedThumbColor = Color.White,
                                checkedTrackColor = Color.Red,
                            ),
                            onCheckedChange = {
                                isChecked = it
                            }
                        )
                    }

                }
            }

            Button(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                onClick = {
                    if(makhachhang!=null){
                        var diachi = DiaChi(0,thongtindiachi,makhachhang,hoten,sodienthoai,0)
                        diaChiViewmodel.addDiaChi(diachi)
                    }
                    navController.popBackStack()
                }
            ) {
                Text(
                    "Thêm địa chỉ mới",
                    fontSize = 17.sp,
                    color = Color.White
                )
            }
        }
    }
}







