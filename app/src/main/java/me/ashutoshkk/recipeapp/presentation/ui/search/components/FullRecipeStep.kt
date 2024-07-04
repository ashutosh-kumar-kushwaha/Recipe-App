package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.OtherRecipeDetails
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun FullRecipeStep(
    recipe: RecipeDetails,
    onBackClick: () -> Unit,
    onSimilarRecipeClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        Heading(R.string.get_full_recipe, onBackClick)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = RecipeTheme.paddings.verticalLarge),
            verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalInBetween)
        ) {
            OtherRecipeDetails(recipe = recipe)
        }
        Button(
            text = R.string.get_similar_recipe,
            onClick = onSimilarRecipeClick
        )
    }
}