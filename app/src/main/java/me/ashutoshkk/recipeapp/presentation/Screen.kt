package me.ashutoshkk.recipeapp.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import me.ashutoshkk.recipeapp.R

sealed class Screen(val route: String) {

    data object Dashboard : Screen("dashboard")

    data object Onboarding : Screen("onboarding")

    data object Search : Screen("search")

    data object Recipe : Screen("recipe/{recipeId}") {
        fun createRoute(recipeId: Int) = "recipe/$recipeId"
    }

}

sealed class BottomTabs(
    route: String,
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) : Screen(route) {

    data object Home : BottomTabs(
        "home",
        R.string.home,
        R.drawable.home_filled,
        R.drawable.home_outlined
    )

    data object Favorite : BottomTabs(
        "favorite",
        R.string.favorite,
        R.drawable.heart_filled,
        R.drawable.heart_outlined
    )

}