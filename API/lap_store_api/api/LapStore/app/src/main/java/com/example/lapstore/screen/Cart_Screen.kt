import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.Product
@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Boxtheodoi()
        CardScreen()
    }
}
@Composable
fun StepItem(icon: ImageVector, label: String, isActive: Boolean = false, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = { onClick() }, // Gọi hành động khi nhấn vào icon
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = if (isActive) Color.Red else Color.White,
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isActive) Color.White else Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (isActive) Color.Red else Color.Black
        )
    }
}

@Composable
fun Boxtheodoi() {
    Text(
        text = "< Mua thêm sản phẩm khác",
        fontSize = 18.sp,
        color = Color.Blue,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clickable {
                // Hành động chuyển trang
                println("Chuyển hướng: Mua thêm sản phẩm khác")
            }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFEBEE)) // Màu nền đỏ nhạt
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Các bước trong tiến trình với sự kiện khi nhấn vào từng bước
            StepItem(
                icon = Icons.Default.ShoppingCart,
                label = "Giỏ hàng",
                isActive = true,
                onClick = { println("Giỏ hàng được nhấn") }
            )
            StepItem(
                icon = Icons.Default.Person,
                label = "Thông tin đặt hàng",
                onClick = { println("Thông tin đặt hàng được nhấn") }
            )
            StepItem(
                icon = Icons.Default.AttachMoney,
                label = "Thanh toán",
                onClick = { println("Thanh toán được nhấn") }
            )
            StepItem(
                icon = Icons.Default.CheckCircle,
                label = "Hoàn tất",
                onClick = { println("Hoàn tất được nhấn") }
            )
        }
    }
}
@Composable
fun CardScreen() {
    // Trạng thái danh sách sản phẩm
    val products = remember {
        mutableStateListOf(
            Product(
                name = "Laptop gaming Acer Nitro 16 Phoenix AN16 41 R76E",
                Describe="null",
                discountPrice=31300000,
                originalPrice = 29490000,
                quantity = 1,
                gift = "Đế Tản Nhiệt Cooler Master Notepal C3 - Trị giá: 230.000đ",
                imageRes = 1
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Tiêu đề giỏ hàng
        Text(
            text = "Giỏ hàng của bạn",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Thông tin sản phẩm
        LazyColumn {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        // Hàng đầu tiên: Hình ảnh và tên sản phẩm
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Hình ảnh sản phẩm
//                            Image(
//                                painter = painterResource(id = R.drawable.laptop1),
//                                contentDescription = "Sản phẩm",
//                                modifier = Modifier.size(60.dp)
//                            )

                            // Tên sản phẩm và quà tặng
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = product.name,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Quà tặng: ${product.gift}",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Hàng thứ hai: Điều chỉnh số lượng và hiển thị giá
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Nút trừ, hiển thị số lượng, và nút cộng
                            Row {
                                Button(
                                    onClick = {
                                        if (product.quantity > 1) {
                                            product.quantity -= 1
                                        }
                                    },
                                    modifier = Modifier.size(40.dp),
                                    shape = RoundedCornerShape(0.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text("-", fontSize = 20.sp, color = Color.Black)
                                }

                                Text(
                                    text = "${product.quantity}",
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .align(Alignment.CenterVertically),
                                    fontSize = 16.sp
                                )

                                Button(
                                    onClick = {
                                        product.quantity += 1
                                    },
                                    modifier = Modifier.size(40.dp),
                                    shape = RoundedCornerShape(0.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text("+", fontSize = 20.sp, color = Color.Black)
                                }
                            }

                            // Hiển thị giá bên phải
                            Column(
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "${product.discountPrice * product.quantity}đ",
                                    fontSize = 16.sp,
                                    color = Color.Red
                                )
                                Text(
                                    text = "31.490.000đ", // Giá cũ (nếu có)
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    textDecoration = TextDecoration.LineThrough
                                )
                            }
                        }
                    }
                }
            }
        }

        // Tổng tiền
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tổng tiền:",
                fontSize = 16.sp,
                color = Color.Black
            )
            val total = products.sumOf { it.originalPrice * it.quantity }
            Text(
                text = "${total}đ",
                fontSize = 16.sp,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút "Đặt hàng ngay"
        Button(
            onClick = { /* Xử lý đặt hàng */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(
                text = "ĐẶT HÀNG NGAY",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    onQuantityChange: (Int) -> Unit,
    onRemoveProduct: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Hình ảnh
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = product.name,
                    modifier = Modifier.size(60.dp)
                )

                // Tên sản phẩm và thông tin
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = product.name,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Giá khuyến mãi: ${String.format("%,d", product.discountPrice)}đ",
                        fontSize = 14.sp,
                        color = Color.Red
                    )
                }

                // Nút xóa
                IconButton(onClick = onRemoveProduct) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Xóa sản phẩm",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Điều chỉnh số lượng
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Nút trừ
                Button(
                    onClick = { if (product.quantity > 1) onQuantityChange(product.quantity - 1) },
                    modifier = Modifier.size(40.dp),
                    shape = RoundedCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("-", fontSize = 20.sp, color = Color.Black)
                }

                // Hiển thị số lượng
                Text(
                    text = "${product.quantity}",
                    fontSize = 16.sp,
                    color = Color.Black
                )

                // Nút cộng
                Button(
                    onClick = { onQuantityChange(product.quantity + 1) },
                    modifier = Modifier.size(40.dp),
                    shape = RoundedCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("+", fontSize = 20.sp, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tổng giá
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tổng tiền:",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "${String.format("%,d", product.quantity * product.discountPrice)}đ",
                    fontSize = 16.sp,
                    color = Color.Red
                )
            }
        }
    }
}




