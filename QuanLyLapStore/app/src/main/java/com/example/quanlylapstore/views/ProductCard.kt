import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.quanlylapstore.models.SanPham

@Composable
fun ProductCard(
    sanpham: SanPham,
//    Editonclick:()->Unit,
    Deleteonclick:()->Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(width = 260.dp, height = 550.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Box (
            modifier = Modifier
                .fillMaxSize()
        ){
            Column(modifier = Modifier.padding(10.dp)) {
                AsyncImage(
                    model= sanpham.HinhAnh,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp).size(200.dp),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = sanpham.TenSanPham,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Card(
                    modifier = Modifier.size(width = 270.dp, height = 120.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFCCCCCC)),
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text("CPU: "+sanpham.MaCPU)
                        Text("Card: "+sanpham.MaCardDoHoa)
                        Text("RAM: "+sanpham.MaRAM.toString()+" GB")
                        Text("ROM: "+sanpham.MaROM.toString()+" GB")
                    }

                }
                // Giá sản phẩm
                Text(
                    text = sanpham.Gia.toString()+"đ",
                    fontSize = 16.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Row (
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit, contentDescription = "", tint = Color.Green
                        )
                    }

                    IconButton(
                        onClick = Deleteonclick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete, contentDescription = "",tint = Color.Red
                        )
                    }
                }
            }
        }

    }
}