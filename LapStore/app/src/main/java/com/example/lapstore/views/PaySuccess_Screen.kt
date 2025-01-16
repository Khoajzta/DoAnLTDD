import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lapstore.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaySuccess_Screen(
    navController: NavHostController,
    tentaikhoan:String
){

    val systemUiController = rememberSystemUiController()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                ),
                title = {

                },
            )
        }
    ) {
        SideEffect {
            systemUiController.setStatusBarColor(color = Color.Red, darkIcons = false)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(it).fillMaxWidth().background(color = Color.Red),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.checksuccess),
                    contentDescription = "QR Code",
                    modifier = Modifier.size(30.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    "Đặt hàng thành công",
                    color = Color.White,
                    fontSize = 30.sp
                )
            }
            Button(
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.navigate("${NavRoute.HOME.route}?tentaikhoan=${tentaikhoan}"){
                        popUpTo(0) { inclusive = true }
                    }
                }
            ) {
                Text(
                    "Màn hình chính",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = Color.Red
                )
            }
        }
    }
}