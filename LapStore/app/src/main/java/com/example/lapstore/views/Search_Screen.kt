

import NavRoute
import SanPhamViewModel
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.findFirstRoot
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.EventListener
import coil.compose.AsyncImage
import com.example.lapstore.models.SanPham
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
){

    Scaffold(
        containerColor = Color.Red,
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {

                        }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Red
                ),
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                title = {
                    // Search Bar
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        OutlinedTextField(
                            value = "",
                            onValueChange = {

                            },
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White
                            ),
                            placeholder = {
                                Text(
                                    text = "Bạn cần tìm gì",
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 13.sp
                                    ),
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            },
                            shape = RoundedCornerShape(50)
                        )
                    }
                }

            )

        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {  }
    }

    val sanPhamViewModel:SanPhamViewModel= viewModel()
    var sanphamlist by remember { mutableStateOf<List<SanPham>>(emptyList()) }
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Red, darkIcons = false)
    }

 LazyColumn (){
     items(sanphamlist){
         SanPhamSearhCard(sanPham = it, onClick = {
             navController.navigate(NavRoute.PRODUCTDETAILSCREEN.route)
         })
     }
 }


}
@Composable
fun SanPhamSearhCard(sanPham: SanPham,onClick:()->Unit){

    Card (modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        , colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        onClick = onClick,
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(2.dp)){
        Column (
            modifier = Modifier.padding(10.dp)
        ){
            AsyncImage(model = sanPham.HinhAnh,
                contentDescription = null,
                modifier = Modifier.padding(8.dp).size(100.dp),
                contentScale = ContentScale.Fit)
            Row (modifier = Modifier.padding(5.dp)){
                Text(sanPham.TenSanPham)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(sanPham.MoTa, color = Color.LightGray, fontSize = 15.sp)
                Spacer(modifier = Modifier.padding(5.dp))
                Text("Giá : ${sanPham.Gia}", color =Color.Red, fontSize = 20.sp )
            }
        }


    }
}
