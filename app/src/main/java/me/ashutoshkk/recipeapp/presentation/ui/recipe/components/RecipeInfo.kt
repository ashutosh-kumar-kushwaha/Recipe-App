package me.ashutoshkk.recipeapp.presentation.ui.recipe.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                shape = RoundedCornerShape(12.dp)
            )
            .padding(RecipeTheme.paddings.around),
        verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.vertical),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = RecipeTheme.typography.bodySmall,
            color = RecipeTheme.colorScheme.text3,
        )
        Text(
            text = description,
            style = RecipeTheme.typography.titleMedium,
            color = RecipeTheme.colorScheme.primary,
        )
    }
}