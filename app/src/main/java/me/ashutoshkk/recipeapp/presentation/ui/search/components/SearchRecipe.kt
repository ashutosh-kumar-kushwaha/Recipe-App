package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.domain.model.Recipe
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun SearchRecipe(recipe: Recipe, onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(recipe.id)
            }
            .padding(RecipeTheme.paddings.allMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.horizontal)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.search_result),
            contentDescription = null,
            tint = RecipeTheme.colorScheme.heading,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = recipe.title,
            style = RecipeTheme.typography.bodyMedium,
            color = RecipeTheme.colorScheme.heading,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}