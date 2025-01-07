import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lapstore.models.TaiKhoan
import com.example.lapstore.views.AcccountScreen
import com.example.lapstore.views.CardScreen
import com.example.lapstore.views.HomeScreen
import com.example.lapstore.views.LoginScreen
import com.example.lapstore.views.ProductDetail_Screen

sealed class NavRoute(val route: String) {
    object HOME : NavRoute("home_screen")
    object ACCOUNT: NavRoute("account_screen")
    object CARD: NavRoute("card_screen")
    object PRODUCTDETAILSCREEN: NavRoute("productdetail_screen")
    object LOGINSCREEN: NavRoute("login_screen")
}

@Composable
fun NavgationGraph(
    navController: NavHostController,
    viewmodel: SanPhamViewModel,
    hinhAnhViewModel: HinhAnhViewModel
) {
    NavHost(navController = navController, startDestination = NavRoute.HOME.route) {
        // HomeScreen không có tham số (Chưa đăng nhập)
        composable(NavRoute.HOME.route) {
            HomeScreen(navController, viewmodel, null)
        }

        // HomeScreen có tham số (Đã đăng nhập)
        composable(
            route = NavRoute.HOME.route + "?tentaikhoan={tentaikhoan}",
            arguments = listOf(
                navArgument("tentaikhoan") { type = NavType.StringType },
            )
        ) {
            val tentaikhoan = it.arguments?.getString("tentaikhoan")
            HomeScreen(navController, viewmodel, tentaikhoan)
        }

        // Màn hình tài khoản
        composable(NavRoute.ACCOUNT.route) {
            AcccountScreen()
        }

        // Màn hình giỏ hàng
        composable(NavRoute.CARD.route) {
            CardScreen()
        }

        // Màn hình đăng nhập
        composable(NavRoute.LOGINSCREEN.route) {
            LoginScreen(navController)
        }

        // Màn hình chi tiết sản phẩm
        composable(
            route = NavRoute.PRODUCTDETAILSCREEN.route + "?id={id}",
            arguments = listOf(navArgument("id") { nullable = true })
        ) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                ProductDetail_Screen(navController = navController, id.toString(), viewModel = viewmodel, hinhAnhViewModel)
            }
        }
    }
}
