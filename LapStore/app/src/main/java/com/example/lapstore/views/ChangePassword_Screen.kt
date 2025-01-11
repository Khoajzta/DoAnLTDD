import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lapstore.models.TaiKhoan
import com.example.lapstore.viewmodels.TaiKhoanViewModel

@Composable
fun ChangePasswordSection(
    tentaikhoan: String
) {

    var matkhaucu by remember { mutableStateOf("") }
    var matkhaumoi by remember { mutableStateOf("") }
    var kiemtramkmoi by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    var taiKhoanViewModel: TaiKhoanViewModel = viewModel()

    var taikhoan = taiKhoanViewModel.taikhoan

    taiKhoanViewModel.getSanTaiKhoanByTentaikhoan(tentaikhoan)

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Đổi mật khẩu", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = matkhaucu,
                label = { Text("Mật khẩu cũ") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedLabelColor = Color.Red
                ),
                shape = RoundedCornerShape(17.dp),
                onValueChange = { matkhaucu = it }
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = matkhaumoi,
                label = { Text("Mật khẩu mới") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedLabelColor = Color.Red
                ),
                shape = RoundedCornerShape(17.dp),
                onValueChange = { matkhaumoi = it }
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = kiemtramkmoi,
                label = { Text("Nhập lại mật khẩu") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    focusedLabelColor = Color.Red
                ),
                shape = RoundedCornerShape(17.dp),
                onValueChange = { kiemtramkmoi = it }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (taikhoan != null) {
                        if (matkhaucu != "" && matkhaumoi != "" && kiemtramkmoi != "") {
                            if (matkhaucu != taikhoan.MatKhau) {
                                Log.d("mật khẩu cũ", "không chính xác")
                                showDialog = true
                            } else if (matkhaumoi != kiemtramkmoi) {
                                Log.d("mật khẩu mới", "Xác nhận mật khẩu không chính xác")
                                showDialog = true
                            } else {
                                showDialog = true
                            }
                        } else {
                            showDialog = true
                            Log.d("trống", "Vui lòng nhập thông tin")
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ĐỔI MẬT KHẨU", color = Color.White)
            }
        }
    }

    // Hiển thị Dialog xác nhận đổi mật khẩu
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false }, // Đóng khi nhấn ngoài dialog
            text = {
                if (taikhoan != null) {
                    if(matkhaucu != "" && matkhaumoi != "" && kiemtramkmoi != ""){
                        if (matkhaucu != taikhoan.MatKhau) {
                            Text("Mật khẩu cũ không trùng khớp")
                        }
                        else if(matkhaumoi != kiemtramkmoi){
                            Text("Xác nhận mật khẩu không chính xác")
                        }
                        else{
                            Text("Đổi mật khẩu thành công")
                        }
                    }
                    else{
                        Text("Vui lòng nhập đầy đủ thông tin")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        if (taikhoan != null) {
                            var taikhoan = TaiKhoan(tentaikhoan, taikhoan.MaKhachHang, matkhaumoi)
                            taiKhoanViewModel.updateTaiKhoan(taikhoan)
                        }
                    }
                ) {
                    Text("OK")
                }
            },
        )
    }
}

