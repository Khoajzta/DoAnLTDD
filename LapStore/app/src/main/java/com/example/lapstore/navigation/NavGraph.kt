import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lapstore.views.AcccountScreen
import com.example.lapstore.views.CardScreen
import com.example.lapstore.views.HomeScreen

sealed class NavRoute(val route: String) {
    object HOME : NavRoute("home_screen")
    object ACCOUNT: NavRoute("account_screen")
    object CARD: NavRoute("card_screen")
}

@Composable
fun NavgationGraph(navController: NavHostController,viewmodel:SanPhamViewModel) {
    NavHost(navController = navController, startDestination = NavRoute.HOME.route){
        composable(NavRoute.HOME.route){
            HomeScreen(navController,viewmodel)
        }
        composable(NavRoute.ACCOUNT.route){
            AcccountScreen()
        }
        composable(NavRoute.CARD.route){
            CardScreen()
        }
    }

}