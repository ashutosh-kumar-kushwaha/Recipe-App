package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
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
    var offset by remember { mutableStateOf(IntOffset(0, 0)) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = offset.y.dp)
            .navigationBarsPadding()
    ) {
        Heading(R.string.full_recipe, onBackClick, offset) {
            offset = it
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
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