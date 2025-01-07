package com.example.lapstore.views

import NavRoute
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lapstore.models.TaiKhoan
import com.example.lapstore.ui.TaiKhoanStore
import com.example.lapstore.viewmodels.TaiKhoanViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
) {
    val systemUiController = rememberSystemUiController()

    var tendangnhap by remember { mutableStateOf("") }
    var matkhau by remember { mutableStateOf("") }

    val taiKhoanViewModel: TaiKhoanViewModel = viewModel()

    // Gán null nếu không tìm thấy tài khoản
    var taikhoan by remember { mutableStateOf<TaiKhoan?>(null) }

    val openDialog = remember { mutableStateOf(false) }
    val openDialognull = remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                title = {

                }

            )

        }
    ) {
        SideEffect {
            systemUiController.setStatusBarColor(color = Color.White, darkIcons = false)
        }
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "ĐĂNG NHẬP",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp),
                color = Color.Red
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = tendangnhap,
                label = {
                    Text(
                        "Tên đăng nhập"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedLabelColor = Color.Red
                ),
                shape = RoundedCornerShape(17.dp),
                onValueChange = {
                    tendangnhap = it
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = matkhau,
                label = {
                    Text(
                        "Mật khẩu"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedLabelColor = Color.Red
                ),
                shape = RoundedCornerShape(17.dp),
                onValueChange = {
                    matkhau = it
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .shadow(10.dp),
                shape = RoundedCornerShape(17.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                onClick = {
                    if (tendangnhap.isEmpty() || matkhau.isEmpty()) {
                        openDialognull.value = true
                    } else {
                        // Kiểm tra tài khoản
                        taiKhoanViewModel.KiemTraDangNhap(tendangnhap, matkhau)

                        // Kiểm tra xem tài khoản có tồn tại không
                        taikhoan = taiKhoanViewModel.taikhoan

                        if (taikhoan != null) {
                            // Nếu tài khoản hợp lệ, chuyển sang màn hình Home
                            navController.navigate(NavRoute.HOME.route + "?tentaikhoan=${tendangnhap}")
                        }
//                        } else {
//                            // Nếu tài khoản không hợp lệ, hiển thị dialog lỗi
//                            openDialog.value = true
//                        }
                    }
                }
            ) {
                Text(
                    "ĐĂNG NHẬP",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Bạn chưa có tài khoản?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                TextButton(
                    onClick = {

                    }
                ) {
                    Text(
                        "Đăng ký ngay!",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }
            }

            // Hiển thị dialog nếu thông tin đăng nhập sai
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = { openDialog.value = false }, // Đóng khi nhấn ngoài dialog
                    title = { Text("Tiêu đề Dialog") },
                    text = { Text("Tên đăng nhập hoặc mật khẩu không chính xác.") },
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                                tendangnhap = ""
                                matkhau = ""
                            }
                        ) {
                            Text("OK")
                        }
                    },
                )
            }

            // Hiển thị dialog nếu thông tin chưa đầy đủ
            if (openDialognull.value) {
                AlertDialog(
                    onDismissRequest = { openDialognull.value = false }, // Đóng khi nhấn ngoài dialog
                    title = { Text("Tiêu đề Dialog") },
                    text = { Text("Vui lòng nhập đầy đủ thông tin.") },
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialognull.value = false
                                tendangnhap = ""
                                matkhau = ""
                            }
                        ) {
                            Text("OK")
                        }
                    },
                )
            }
        }
    }
}






