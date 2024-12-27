import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class NavRoute(val route: String) {
    object HOME : NavRoute("home_screen")
    object ACCOUNT: NavRoute("account_screen")
    object CARD: NavRoute("card_screen")
}

@Composable
fun NavgationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoute.HOME.route){
        composable(NavRoute.HOME.route){
            HomeScreen(navController)
        }
        composable(NavRoute.ACCOUNT.route){
            AcccountScreen()
        }
        composable(NavRoute.CARD.route){
            CardScreen()
        }
    }

}