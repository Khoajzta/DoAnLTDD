import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lapstore.views.AcccountScreen
import com.example.lapstore.views.HomeScreen
import com.example.lapstore.views.LoginScreen
import com.example.lapstore.views.ProductDetail_Screen

import com.example.tapdieuhuong.MainContent


sealed class NavRoute(val route: String) {
    object HOME : NavRoute("home_screen")
    object ACCOUNT: NavRoute("account_screen")
    object CARD: NavRoute("card_screen")
    object PRODUCTDETAILSCREEN: NavRoute("productdetail_screen")
    object LOGINSCREEN: NavRoute("login_screen")
}

@Composable
fun NavgationGraph(navController: NavHostController,viewmodel:SanPhamViewModel,hinhAnhViewModel: HinhAnhViewModel) {
    NavHost(navController = navController, startDestination = NavRoute.HOME.route){
        composable(NavRoute.HOME.route){
            HomeScreen(navController,viewmodel)
        }
        composable(NavRoute.ACCOUNT.route){
            AcccountScreen()
        }
        composable(NavRoute.CARD.route){
            MainContent()
        }
        composable(NavRoute.LOGINSCREEN.route){
            LoginScreen()
        }
        composable(
            route = NavRoute.PRODUCTDETAILSCREEN.route+"?id={id}",
            arguments = listOf(navArgument("id"){nullable = true})
        ){
            var id = it.arguments?.getString("id")
            if(id != null){
                ProductDetail_Screen(navController = navController,id.toString(),viewModel = viewmodel,hinhAnhViewModel)
            }
        }
    }

}