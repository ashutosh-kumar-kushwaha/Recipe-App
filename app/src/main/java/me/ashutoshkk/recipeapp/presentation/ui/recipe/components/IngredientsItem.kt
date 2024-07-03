package me.ashutoshkk.recipeapp.presentation.ui.recipe.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import me.ashutoshkk.recipeapp.common.Constants.IMAGE_URL
import me.ashutoshkk.recipeapp.domain.model.Ingredient
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun Ingredient(
    name: String,
    imageUrl: String
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalInBetweenSmall),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter = rememberAsyncImagePainter(model = imageUrl)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(86.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = RecipeTheme.colorScheme.border,
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop
        )
        Text(
            text = name,
            style = RecipeTheme.typography.bodySmall,
            color = RecipeTheme.colorScheme.text,
            fontWeight = FontWeight.Medium
        )
    }
}