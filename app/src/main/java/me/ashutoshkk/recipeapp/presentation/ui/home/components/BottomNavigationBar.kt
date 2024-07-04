package me.ashutoshkk.recipeapp.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import me.ashutoshkk.recipeapp.presentation.BottomTabs
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val bottomTabs = listOf(
        BottomTabs.Home,
        BottomTabs.Favorite
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (bottomTabs.map { it.route }.contains(currentRoute)) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            containerColor = RecipeTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(RecipeTheme.colorScheme.background2)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                        .weight(1f)
                ) {
                    bottomTabs.forEach { item ->
                        val selected = currentRoute == item.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(
                                        id = if (selected) item.selectedIcon else item.unselectedIcon
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = item.title),
                                    style = RecipeTheme.typography.bodySmall,
                                    modifier = Modifier.offset(y = (-4).dp)
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = RecipeTheme.colorScheme.primary,
                                unselectedIconColor = RecipeTheme.colorScheme.text3,
                                selectedTextColor = RecipeTheme.colorScheme.primary,
                                unselectedTextColor = RecipeTheme.colorScheme.text3,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    }
}