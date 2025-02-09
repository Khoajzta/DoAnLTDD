import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lapstore.views.AcccountScreen
import com.example.lapstore.views.HomeScreen
<<<<<<< Updated upstream
//<<<<<<< Updated upstream
//=======
import com.example.lapstore.views.ProductDetail_Screen
import com.example.tapdieuhuong.MainContent
//>>>>>>> Stashed changes
=======
import com.example.lapstore.views.MainContent
>>>>>>> Stashed changes

sealed class NavRoute(val route: String) {
    object HOME : NavRoute("home_screen")
<<<<<<< Updated upstream
    object ACCOUNT: NavRoute("account_screen")
    object CARD: NavRoute("card_screen")
=======
    object ACCOUNT : NavRoute("account_screen")
    object CART : NavRoute("cart_screen")
    object PRODUCTDETAILSCREEN : NavRoute("productdetail_screen")
    object LOGINSCREEN : NavRoute("login_screen")
    object PAYSCREEN : NavRoute("pay_screen")
    object PAYSUCCESS : NavRoute("paysuccess_screen")
    object QUANLYDONHANG : NavRoute("quanlydonhang_screen")
    object DIACHISCREEN : NavRoute("diachi_screen")
    object SEARCHSCREEN:NavRoute("search_screen")
>>>>>>> Stashed changes
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
            MainContent()
        }
<<<<<<< Updated upstream
=======

        // Màn hình giỏ hàng
        composable(
            route = "${NavRoute.CART.route}?makhachhang={makhachhang}&tentaikhoan={tentaikhoan}",
            arguments = listOf(
                navArgument("makhachhang") { type = NavType.StringType },
                navArgument("tentaikhoan") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val makhachhang = backStackEntry.arguments?.getString("makhachhang") ?: ""
            val tentaikhoan = backStackEntry.arguments?.getString("tentaikhoan") ?: ""
            CartScreen(navController,makhachhang,tentaikhoan)
        }

        composable(NavRoute.LOGINSCREEN.route) {
            LoginScreen(navController,taiKhoanViewModel)
        }

        composable(
            route = NavRoute.PRODUCTDETAILSCREEN.route + "?id={id}&makhachhang={makhachhang}",
            arguments = listOf(
                navArgument("id") { nullable = true },
                navArgument("makhachhang") { nullable = true },
            )
        ) {
            val id = it.arguments?.getString("id")
            val makhachhang = it.arguments?.getString("makhachhang")

            if (id != null) {
                ProductDetail_Screen(
                    navController = navController,
                    id = id,
                    makhachhang = makhachhang,
                    tentaikhoan = null,
                    viewModel = viewmodel,
                    hinhAnhViewModel = hinhAnhViewModel
                )
            }
        }

        composable(
            route = NavRoute.PRODUCTDETAILSCREEN.route + "?id={id}&makhachhang={makhachhang}&tentaikhoan={tentaikhoan}",
            arguments = listOf(
                navArgument("id") { nullable = true },
                navArgument("makhachhang") { nullable = true },
                navArgument("tentaikhoan") { type = NavType.StringType }
            )
        ) {
            val id = it.arguments?.getString("id")
            val makhachhang = it.arguments?.getString("makhachhang")
            val tentaikhoan = it.arguments?.getString("tentaikhoan")

            if (id != null) {
                ProductDetail_Screen(
                    navController = navController,
                    id = id,
                    makhachhang = makhachhang,
                    tentaikhoan = tentaikhoan.toString(),
                    viewModel = viewmodel,
                    hinhAnhViewModel = hinhAnhViewModel
                )
            }
        }

        composable(
            route = NavRoute.PAYSCREEN.route + "?selectedProducts={selectedProducts}&tongtien={tongtien}&tentaikhoan={tentaikhoan}",
            arguments = listOf(
                navArgument("selectedProducts") { type = NavType.StringType },
                navArgument("tongtien") { nullable = true },
                navArgument("tentaikhoan") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Lấy chuỗi selectedProducts từ tham số điều hướng
            val selectedProductsString = backStackEntry.arguments?.getString("selectedProducts")

            // Gọi hàm parseSelectedProducts để chuyển chuỗi thành danh sách Triple<Int, Int, Int>
            val selectedProducts = selectedProductsString?.let { parseSelectedProducts(it) } ?: emptyList()

            // Chuyển đổi tongtien từ String sang Int, nếu không có giá trị thì mặc định là 0
            val tongtien = backStackEntry.arguments?.getString("tongtien")?.toIntOrNull() ?: 0
            val tentaikhoan = backStackEntry.arguments?.getString("tentaikhoan") ?: ""

            // Chuyển sang màn hình PayScreen với các tham số cần thiết
            PayScreen(navController = navController, selectedProducts = selectedProducts, tongtien = tongtien,tentaikhoan)
        }

        composable(
            route = "${NavRoute.PAYSUCCESS.route}?tentaikhoan={tentaikhoan}",
            arguments = listOf(navArgument("tentaikhoan") { type = NavType.StringType })
        ) { backStackEntry ->
            val tentaikhoan = backStackEntry.arguments?.getString("tentaikhoan") ?: ""
            PaySuccess_Screen(navController,tentaikhoan)
        }

        composable(
            route = "${NavRoute.QUANLYDONHANG.route}?makhachhang={makhachhang}",
            arguments = listOf(navArgument("makhachhang") { type = NavType.IntType }) // Thay đổi StringType thành IntType
        ) { backStackEntry ->
            val makhachhang = backStackEntry.arguments?.getInt("makhachhang") ?: 0 // Lấy makhachhang dưới dạng Int
            CartManagementSection(navController,makhachhang)
        }

        composable(
            route = "${NavRoute.DIACHISCREEN.route}?makhachhang={makhachhang}",
            arguments = listOf(navArgument("makhachhang") { type = NavType.IntType }) // Thay đổi StringType thành IntType
        ) { backStackEntry ->
            val makhachhang = backStackEntry.arguments?.getInt("makhachhang") ?: 0 // Lấy makhachhang dưới dạng Int
            AddressManagementScreen(navController,makhachhang)
        }
        composable(
            route="${NavRoute.SEARCHSCREEN.route}?masanpham={masanpham}",
            arguments = listOf(navArgument("makhachhang") { type = NavType.IntType })
        ) {
            backStackEntry->
            val masanpham = backStackEntry.arguments?.getInt("masanpham") ?: 0
            SearchScreen(navController=navController)
        }

>>>>>>> Stashed changes
    }

}