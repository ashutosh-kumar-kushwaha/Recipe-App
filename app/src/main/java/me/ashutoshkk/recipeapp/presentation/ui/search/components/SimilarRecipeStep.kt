package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.domain.model.Recipe
import me.ashutoshkk.recipeapp.presentation.ui.home.components.RecipeCard
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun SimilarRecipeStep(
    similarRecipe: List<Recipe>,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        Heading(R.string.similar_recipe, onBackClick)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.vertical),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = RecipeTheme.paddings.vertical)
        ) {
            items(similarRecipe) {
                RecipeCard(
                    recipe = it
                ) {}
            }
        }
    }
}