package com.example.quanlylapstore.views

import SanPhamViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.quanlylapstore.navigation.NavgationGraph
import com.example.quanlylapstore.ui.theme.QuanLyLapStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuanLyLapStoreTheme {
                val viewModel = SanPhamViewModel()
                val navController = rememberNavController()

                NavgationGraph(navController,viewModel)
            }
        }
    }
}
