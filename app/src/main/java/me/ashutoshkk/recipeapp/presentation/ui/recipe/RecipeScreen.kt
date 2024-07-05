package me.ashutoshkk.recipeapp.presentation.ui.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.common.Constants.IMAGE_URL
import me.ashutoshkk.recipeapp.presentation.ui.home.components.ProgressBar
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.ErrorScreen
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.Ingredient
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.OtherRecipeDetails
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.RecipeImage
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.RecipeInfo
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun RecipeScreen() {
    val viewModel: RecipeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalInBetween)
        ) {
            if (uiState.recipe != null) {
                val recipe = uiState.recipe!!
                RecipeImage(
                    name = recipe.title,
                    imageUrl = recipe.image,
                    isFavorite = uiState.isFavorite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {
                    viewModel.toggleFavorite()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = RecipeTheme.paddings.horizontal),
                    horizontalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.horizontal),
                ) {
                    RecipeInfo(
                        title = stringResource(id = R.string.ready_in),
                        description = "${recipe.readyInMinutes} min",
                        modifier = Modifier.weight(1f)
                    )
                    RecipeInfo(
                        title = stringResource(id = R.string.servings),
                        description = recipe.servings.toString(),
                        modifier = Modifier.weight(1f)
                    )
                    RecipeInfo(
                        title = stringResource(id = R.string.price_serving),
                        description = recipe.pricePerServing.toString(),
                        modifier = Modifier.weight(1f)
                    )
                }
                Text(
                    text = stringResource(id = R.string.ingredients),
                    style = RecipeTheme.typography.titleSmall,
                    color = RecipeTheme.colorScheme.text,
                    modifier = Modifier.padding(
                        horizontal = RecipeTheme.paddings.horizontal
                    ),
                    fontWeight = FontWeight.Bold
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = RecipeTheme.paddings.horizontal),
                    horizontalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.horizontal)
                ) {
                    items(
                        items = recipe.ingredients,
                    ) {
                        Ingredient(it.name, IMAGE_URL.plus(it.image))
                    }
                }
                OtherRecipeDetails(recipe = recipe)
            } else if (uiState.isLoading) {
                ProgressBar()
            } else {
                ErrorScreen {
                    viewModel.fetchRecipeDetails()
                }
            }
        }

        if (!uiState.error.isNullOrBlank()) {
            LaunchedEffect(Unit) {
                snackbarHostState.showSnackbar(
                    message = uiState.error!!
                )
                viewModel.resetErrorMessage()
            }
        }
    }

}