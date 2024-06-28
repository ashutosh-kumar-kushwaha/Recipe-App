package me.ashutoshkk.recipeapp.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import me.ashutoshkk.recipeapp.domain.model.Recipe
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme
import kotlin.random.Random

@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick()
            }
    ) {
        val painter = rememberAsyncImagePainter(model = recipe.image)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
        Column(
            modifier = Modifier
                .padding(
                    start = RecipeTheme.paddings.horizontal,
                    top = RecipeTheme.paddings.vertical,
                    bottom = RecipeTheme.paddings.vertical,
                    end = RecipeTheme.paddings.horizontalSmall
                )
        ) {
            Text(
                text = recipe.title,
                style = RecipeTheme.typography.titleSmall
            )
            Text(
                text = "Ready in ${Random.nextInt(30, 50)} min",
                style = RecipeTheme.typography.labelLarge
            )
        }
    }
}