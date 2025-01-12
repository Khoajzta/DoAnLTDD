package com.example.lapstore.views

import NavRoute
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lapstore.models.TaiKhoan
import com.example.lapstore.viewmodels.TaiKhoanViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    taiKhoanViewModel: TaiKhoanViewModel
) {

    var snackbarHostState = remember {
        SnackbarHostState()
    }

    var scope = rememberCoroutineScope()

    val systemUiController = rememberSystemUiController()

    var tendangnhap by remember { mutableStateOf("nguyenvana") }
    var matkhau by remember { mutableStateOf("12345678") }

    val loginResult = taiKhoanViewModel.loginResult.value

    var openDialog by remember { mutableStateOf(false) }

    taiKhoanViewModel.kiemTraDangNhap(tendangnhap, matkhau)

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White),
                title = {}
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
                label = { Text("Tên đăng nhập") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedLabelColor = Color.Red
                ),
                shape = RoundedCornerShape(17.dp),
                onValueChange = { tendangnhap = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = matkhau,
                label = { Text("Mật khẩu") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedLabelColor = Color.Red
                ),
                shape = RoundedCornerShape(17.dp),
                onValueChange = { matkhau = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .shadow(10.dp),
                shape = RoundedCornerShape(17.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                onClick = {
                    if(tendangnhap == "" || matkhau ==""){
                        openDialog = true
                    }
                    taiKhoanViewModel.kiemTraDangNhap(tendangnhap, matkhau)
                    if(loginResult!=null){
                        if(loginResult.result == true){
                            navController.navigate("${NavRoute.HOME.route}?tentaikhoan=${tendangnhap}"){
                                popUpTo(0) { inclusive = true }
                            }
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Đăng nhập thành công"
                                )
                            }
                        }
                        else{
                            openDialog = true
                        }
                    }
                },
            ) {
                Text(
                    text = "ĐĂNG NHẬP",
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
                    onClick = {}
                ) {
                    Text(
                        "Đăng ký ngay!",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }
            }

            SnackbarHost(
                modifier = Modifier.padding(30.dp),
                hostState = snackbarHostState
            )

            if (openDialog == true) {
                AlertDialog(
                    onDismissRequest = { openDialog = false }, // Đóng khi nhấn ngoài dialog
                    text = {
                        if(loginResult!=null){
                            if(tendangnhap == "" || matkhau ==""){
                                Text("Vui lòng nhập đầy đủ thông tin")
                            }
                            else if(loginResult.result == false){
                                Text("Tài khoản hoặc mật khẩu không chính xác")
                            }
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialog = false
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









