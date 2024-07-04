package me.ashutoshkk.recipeapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.ashutoshkk.recipeapp.presentation.Screen
import me.ashutoshkk.recipeapp.presentation.ui.dashboard.DashboardScreen
import me.ashutoshkk.recipeapp.presentation.ui.onboarding.OnboardingScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(route = Screen.Dashboard.route) {
            DashboardScreen()
        }
    }
}