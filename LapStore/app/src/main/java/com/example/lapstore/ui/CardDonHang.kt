import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lapstore.models.HoaDonBan
import com.example.lapstore.views.formatDate

@Composable
fun CardDonHang(hoaDonBan: HoaDonBan){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth().padding(5.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Mã Hóa Đơn: ${hoaDonBan.MaHoaDonBan}",
            )
            Text(text = "Ngày Đặt Hàng: ${formatDate(hoaDonBan.NgayDatHang)}")
            Text(text = "Tổng Tiền: ${hoaDonBan.TongTien} VND")
            Text(text = "Trạng Thái: ${hoaDonBan.TrangThai}")
        }
    }
}