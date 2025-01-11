import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.lapstore.models.SanPham
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayScreen(
    navController: NavHostController,
    selectedProducts: List<Pair<Int, Int>>,
    tongtien: Int
) {
    val sanPhamViewModel: SanPhamViewModel = viewModel()

    // State để lưu danh sách sản phẩm đã lấy thông tin
    val danhsachsanham by sanPhamViewModel.danhsachSanPham.collectAsState(initial = emptyList())

    val systemUiController = rememberSystemUiController()

    // State để lưu trữ lựa chọn phương thức thanh toán
    var selectedPaymentMethod by remember { mutableStateOf("Tiền mặt") }

    // Lấy thông tin sản phẩm khi màn hình được tạo
    LaunchedEffect(selectedProducts) {
        selectedProducts.forEach { pair ->
            sanPhamViewModel.getSanPhamById2(pair.first.toString())
        }
    }
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.White, darkIcons = false)
    }
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text("Thanh toán")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "",
                            tint = Color.Red
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = Color.White) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Tổng tiền: ${formatGiaTien(tongtien)}",
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            // Xử lý đặt hàng, kèm theo selectedPaymentMethod
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Đặt hàng")
                    }
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(10.dp)
        ) {
            items(danhsachsanham) { sanpham ->
                selectedProducts.forEach { pair ->
                    if (sanpham.MaSanPham == pair.first)
                        ProductItem(sanpham, pair.second)
                }
            }
            item {
                Text(
                    text = "Phương thức thanh toán",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tiền mặt",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                    RadioButton(
                        selected = selectedPaymentMethod == "Tiền mặt",
                        onClick = { selectedPaymentMethod = "Tiền mặt" },
                        colors = RadioButtonDefaults.colors(
                            unselectedColor = Color.Red,
                            selectedColor = Color.Red
                        )
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Chuyển khoản ngân hàng",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                    RadioButton(
                        selected = selectedPaymentMethod == "Chuyển khoản ngân hàng",
                        onClick = { selectedPaymentMethod = "Chuyển khoản ngân hàng" },
                        colors = RadioButtonDefaults.colors(
                            unselectedColor = Color.Red,
                            selectedColor = Color.Red
                        )
                    )
                }
            }
        }
    }
}



@Composable
fun ProductItem(sanPham: SanPham, soLuong: Int) {
    Row(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = sanPham.HinhAnh,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.fillMaxHeight().padding(top = 10.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                sanPham.TenSanPham
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    sanPham.Gia.toString(),
                    color = Color.Red
                )
                Text(
                    "x$soLuong"
                )
            }
        }
    }
}



