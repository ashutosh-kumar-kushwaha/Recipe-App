package me.ashutoshkk.recipeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.ashutoshkk.recipeapp.presentation.ui.home.HomeScreen
import me.ashutoshkk.recipeapp.presentation.ui.onboarding.OnboardingScreen
import me.ashutoshkk.recipeapp.presentation.ui.search.SearchScreen
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeAppTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val navigateTo = { route: String ->
        navController.navigate(route)
    }
    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(navigateTo)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navigateTo)
        }
        composable(route = Screen.Search.route){
            SearchScreen()
        }
    }
}