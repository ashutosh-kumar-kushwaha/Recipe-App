package me.ashutoshkk.recipeapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.ashutoshkk.recipeapp.presentation.BottomTabs
import me.ashutoshkk.recipeapp.presentation.Screen
import me.ashutoshkk.recipeapp.presentation.ui.favorite.FavoriteScreen
import me.ashutoshkk.recipeapp.presentation.ui.home.HomeScreen
import me.ashutoshkk.recipeapp.presentation.ui.recipe.RecipeScreen
import me.ashutoshkk.recipeapp.presentation.ui.search.SearchScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomTabs.Home.route
    ) {
        composable(route = BottomTabs.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController)
        }
        composable(route = Screen.Recipe.route) {
            RecipeScreen()
        }
        composable(route = BottomTabs.Favorite.route) {
            FavoriteScreen()
        }
    }
}