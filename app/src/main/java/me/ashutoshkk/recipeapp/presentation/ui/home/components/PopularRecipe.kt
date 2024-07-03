package me.ashutoshkk.recipeapp.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import me.ashutoshkk.recipeapp.domain.model.RandomRecipe
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@ExperimentalMaterial3Api
@Composable
fun PopularRecipe(recipe: RandomRecipe, onClick: () -> Unit) {
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
    Card(
        onClick = onClick,
        modifier = Modifier
            .height(156.dp)
            .width(156.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            val painter = rememberAsyncImagePainter(model = recipe.image)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned { coordinates ->
                        sizeImage = coordinates.size
                    },
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = sizeImage.height.toFloat() / 3,
                            endY = sizeImage.height.toFloat()
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = RecipeTheme.paddings.horizontalSmall,
                        vertical = RecipeTheme.paddings.vertical
                    ),
            ) {
                Text(
                    text = recipe.title,
                    color = RecipeTheme.colorScheme.text2,
                    style = RecipeTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(RecipeTheme.paddings.verticalSmall))
                Text(
                    text = "Ready in ${recipe.readyInMinutes} min",
                    color = RecipeTheme.colorScheme.text2,
                    style = RecipeTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PopularRecipePreview() {
    PopularRecipe(
        RandomRecipe(
            id = 1,
            image = "https://via.placeholder.com/200",
            readyInMinutes = 25,
            title = "Shahi Paneer"
        ),
        onClick = {}
    )
}