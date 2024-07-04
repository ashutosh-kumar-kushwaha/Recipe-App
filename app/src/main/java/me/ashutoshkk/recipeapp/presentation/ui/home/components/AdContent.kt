package me.ashutoshkk.recipeapp.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun AdContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = RecipeTheme.paddings.horizontal,
                vertical = RecipeTheme.paddings.vertical
            )
            .background(RecipeTheme.colorScheme.background2)
            .padding(RecipeTheme.paddings.allMedium),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.ad),
            style = RecipeTheme.typography.bodyLarge,
            color = RecipeTheme.colorScheme.heading,
        )
    }
}