package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.domain.model.Recipe
import me.ashutoshkk.recipeapp.presentation.ui.home.components.RecipeCard
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun SimilarRecipeStep(
    similarRecipe: List<Recipe>,
    onBackClick: () -> Unit
) {
    var offset by remember { mutableStateOf(IntOffset(0, 0)) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = offset.y.dp)
            .navigationBarsPadding()
    ) {
        Heading(R.string.similar_recipe, onBackClick, offset) {
            offset = it
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.vertical),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = RecipeTheme.paddings.vertical),
            contentPadding = PaddingValues(
                horizontal = RecipeTheme.paddings.horizontal,
            )
        ) {
            items(similarRecipe) {
                RecipeCard(
                    title = it.title,
                    imageUrl = it.image,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}