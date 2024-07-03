package me.ashutoshkk.recipeapp.presentation.ui.recipe.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun RecipeInfo(
    title: String,
    description: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = RecipeTheme.colorScheme.border,
            )
            .padding(RecipeTheme.paddings.around),
        verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalInBetween)
    ) {
        Text(
            text = title,
            style = RecipeTheme.typography.bodySmall,
            color = RecipeTheme.colorScheme.text3,
        )
        Text(
            text = description,
            style = RecipeTheme.typography.bodyLarge,
            color = RecipeTheme.colorScheme.primary,
        )
    }
}