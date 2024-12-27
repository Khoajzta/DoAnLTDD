import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.provider.FontsContractCompat.Columns
import androidx.navigation.NavHostController
import com.example.lapstore.R

@Composable
fun HomeScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier.padding(10.dp).fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "Banner Image",
                modifier = Modifier.size(width = 500.dp, height = 220.dp)
            )
        }
        item {
            Text(
                text = "Flash sale",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
                textAlign = TextAlign.Start
            )
        }
        item {
            LazyRow {
                item {
                    ProductCard(
                        productName = "Laptop gaming ASUS Vivobook 16X K3605ZF RP634W",
                        productPrice = "17.290.000₫",
                        productImageResId = R.drawable.assus, // Hình ảnh sản phẩm trong tài nguyên
                        onAddToCartClick = {
                            // Xử lý sự kiện khi nhấn "Add to Cart"
                        }
                    )
                }
                item {
                    ProductCard(
                        productName = "Sample Product",
                        productPrice = "$25.99",
                        productImageResId = R.drawable.assus, // Hình ảnh sản phẩm trong tài nguyên
                        onAddToCartClick = {
                            // Xử lý sự kiện khi nhấn "Add to Cart"
                        }
                    )
                }
                item {
                    ProductCard(
                        productName = "Sample Product",
                        productPrice = "$25.99",
                        productImageResId = R.drawable.assus, // Hình ảnh sản phẩm trong tài nguyên
                        onAddToCartClick = {
                            // Xử lý sự kiện khi nhấn "Add to Cart"
                        }
                    )
                }
                item {
                    ProductCard(
                        productName = "Sample Product",
                        productPrice = "$25.99",
                        productImageResId = R.drawable.assus, // Hình ảnh sản phẩm trong tài nguyên
                        onAddToCartClick = {
                            // Xử lý sự kiện khi nhấn "Add to Cart"
                        }
                    )
                }
            }
        }
    }
}
