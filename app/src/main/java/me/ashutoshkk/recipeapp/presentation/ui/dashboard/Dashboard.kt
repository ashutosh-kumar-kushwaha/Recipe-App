package me.ashutoshkk.recipeapp.presentation.ui.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import me.ashutoshkk.recipeapp.presentation.ui.home.components.BottomNavigationBar
import me.ashutoshkk.recipeapp.presentation.ui.navigation.HomeNavGraph

@Composable
fun DashboardScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HomeNavGraph(navController)
        }
    }
}