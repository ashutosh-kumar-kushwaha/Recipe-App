package me.ashutoshkk.recipeapp.presentation.ui.recipe.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import me.ashutoshkk.recipeapp.common.Constants.IMAGE_URL
import me.ashutoshkk.recipeapp.domain.model.Ingredient
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun Ingredient(ingredient: Ingredient) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalInBetween)
    ) {
        val painter = rememberAsyncImagePainter(model = IMAGE_URL.plus(ingredient.image))
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = RecipeTheme.colorScheme.border,
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )
        Text(
            text = ingredient.name,
            style = RecipeTheme.typography.bodySmall,
            color = RecipeTheme.colorScheme.text,
        )
    }
}