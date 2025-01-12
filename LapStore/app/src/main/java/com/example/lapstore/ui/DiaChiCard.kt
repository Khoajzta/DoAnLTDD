import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.lapstore.models.DiaChi
import com.example.lapstore.models.KhachHang

@Composable
fun DiaChiCard(khachhang:KhachHang,diachi:DiaChi){
    Card(

    ) {
        Column {
            Row(

            ) {
                Text(
                    khachhang.HoTen
                )
                Text(
                    khachhang.SoDienThoai
                )
            }
            Text(
                diachi.ThongTinDiaChi
            )
        }
    }
}