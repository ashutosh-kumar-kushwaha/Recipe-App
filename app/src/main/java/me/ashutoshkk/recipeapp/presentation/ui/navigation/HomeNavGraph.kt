package me.ashutoshkk.recipeapp.presentation.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeNavGraph(navController: NavHostController) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = BottomTabs.Home.route
        ) {
            composable(route = BottomTabs.Home.route) {
                HomeScreen(navController, this)
            }
            composable(route = Screen.Search.route) {
                SearchScreen(navController, this)
            }
            composable(route = Screen.Recipe.route) {
                RecipeScreen()
            }
            composable(route = BottomTabs.Favorite.route) {
                FavoriteScreen(navController)
            }
        }
    }
}