package me.ashutoshkk.recipeapp.presentation

sealed class Screen(val route: String) {

    data object Home: Screen("home")

    data object Onboarding: Screen("onboarding")

    data object Search: Screen("search")

}