package me.ashutoshkk.recipeapp.presentation

sealed class Screen(val route: String) {

    data object Home: Screen("home")

}