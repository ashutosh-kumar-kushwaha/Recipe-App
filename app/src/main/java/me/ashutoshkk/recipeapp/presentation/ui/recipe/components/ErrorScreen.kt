package me.ashutoshkk.recipeapp.presentation.ui.recipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun ErrorScreen(onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(RecipeTheme.paddings.allLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.something_went_wrong),
            style = RecipeTheme.typography.titleLarge,
            color = RecipeTheme.colorScheme.text
        )
        Button(onClick = onRetryClick) {
            Text(
                text = stringResource(id = R.string.retry),
                style = RecipeTheme.typography.titleLarge,
            )
        }
    }
}