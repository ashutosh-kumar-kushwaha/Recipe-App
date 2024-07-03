package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.common.Constants.IMAGE_URL
import me.ashutoshkk.recipeapp.domain.model.Ingredient
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.Ingredient
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun IngredientsStep(
    ingredients: List<Ingredient>,
    onFullRecipeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalInBetween),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = RecipeTheme.paddings.vertical)
        ) {
            items(ingredients) {
                Ingredient(
                    name = it.name,
                    imageUrl = IMAGE_URL.plus(it.image)
                )
            }
        }
        Button(
            text = R.string.get_full_recipe,
            onClick = onFullRecipeClick
        )
    }
}