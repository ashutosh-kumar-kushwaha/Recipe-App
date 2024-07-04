package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.common.Constants.IMAGE_URL
import me.ashutoshkk.recipeapp.domain.model.Ingredient
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.Ingredient
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun IngredientsStep(
    ingredients: List<Ingredient>,
    onBackClick: () -> Unit,
    onFullRecipeClick: () -> Unit,
) {
    var offset by remember { mutableStateOf(IntOffset(0, 0)) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = offset.y.dp)
            .navigationBarsPadding()
    ) {
        Heading(R.string.ingredients, onBackClick, offset) {
            offset = it
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.vertical),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = RecipeTheme.paddings.vertical)
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