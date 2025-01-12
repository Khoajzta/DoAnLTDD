import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lapstore.models.HoaDonBan
import com.example.lapstore.views.formatDate

@Composable
fun CardDonHang(hoaDonBan: HoaDonBan,ishuy:Boolean){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Cột chứa thông tin hóa đơn
            Column(
                modifier = Modifier.weight(1f) // Cột chiếm không gian linh hoạt
            ) {
                Text(
                    text = "Mã Hóa Đơn: ${hoaDonBan.MaHoaDonBan}",
                )
                Text(text = "Ngày Đặt Hàng: ${formatDate(hoaDonBan.NgayDatHang)}")
                Text(text = "Tổng Tiền: ${formatGiaTien(hoaDonBan.TongTien)}")
            }

            // Nút Hủy
            if (ishuy) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 8.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxHeight(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            // Hành động khi bấm nút Hủy
                        }
                    ) {
                        Text("Hủy")
                    }
                }
            }
        }
    }


}