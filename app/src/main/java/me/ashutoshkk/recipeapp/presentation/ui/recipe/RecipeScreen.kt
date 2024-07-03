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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.Ingredient
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.RecipeImage
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.RecipeInfo
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun RecipeScreen() {
    val viewModel: RecipeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            uiState.recipe?.let { recipe ->
                RecipeImage(
                    name = recipe.title,
                    imageUrl = recipe.image,
                    isAddedToWaitlist = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = RecipeTheme.paddings.horizontal)
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
                    color = RecipeTheme.colorScheme.text
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = RecipeTheme.paddings.horizontal),
                    horizontalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.horizontal)
                ) {
                    items(
                        items = recipe.ingredients,
                        key = { it.id }
                    ) {
                        Ingredient(it)
                    }
                }
            }
        }
    }

}