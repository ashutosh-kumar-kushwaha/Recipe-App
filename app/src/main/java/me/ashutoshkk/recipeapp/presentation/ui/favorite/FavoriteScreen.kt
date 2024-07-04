package me.ashutoshkk.recipeapp.presentation.ui.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.presentation.Screen
import me.ashutoshkk.recipeapp.presentation.ui.home.components.RecipeCard
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun FavoriteScreen(navController: NavHostController) {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val favoriteRecipe by viewModel.favoriteRecipe.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(RecipeTheme.paddings.allMedium),
        verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalInBetween)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.favorite_recipes),
                style = RecipeTheme.typography.titleMedium,
                color = RecipeTheme.colorScheme.heading
            )
        }
        items(
            items = favoriteRecipe,
            key = { it.id }
        ) {
            RecipeCard(
                title = it.title,
                imageUrl = it.image,
                readyInMinutes = it.readyInMinutes,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                navController.navigate(Screen.Recipe.createRoute(it.id))
            }
        }
    }
}