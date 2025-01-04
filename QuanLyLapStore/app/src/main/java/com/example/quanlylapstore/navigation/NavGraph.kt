package com.example.quanlylapstore.navigation

import SanPhamViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quanlylapstore.views.HomeScreen
import com.example.quanlylapstore.views.QuanLySanPhamScreen

sealed class NavRoute(val route: String) {
    object HOME : NavRoute("home_screen")
    object QUANLYSANPHAM: NavRoute("qlsanpham_screen")
}

@Composable
fun NavgationGraph(navController: NavHostController, viewmodel:SanPhamViewModel) {
    NavHost(navController = navController, startDestination = NavRoute.HOME.route){
        composable(NavRoute.HOME.route){
            HomeScreen(navController)
        }
        composable(NavRoute.QUANLYSANPHAM.route){
            QuanLySanPhamScreen(navController,viewmodel)
        }
    }

}