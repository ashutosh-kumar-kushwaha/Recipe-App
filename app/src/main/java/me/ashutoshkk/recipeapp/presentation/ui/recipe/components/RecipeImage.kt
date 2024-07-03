package me.ashutoshkk.recipeapp.presentation.ui.recipe.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun RecipeImage(
    name: String,
    imageUrl: String,
    isAddedToWaitlist: Boolean,
    modifier: Modifier,
    onWaitlistClick: () -> Unit
) {
    val painter = rememberAsyncImagePainter(model = imageUrl)
    var sizeImage by remember { mutableStateOf(IntSize.Zero) }
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates ->
                    sizeImage = coordinates.size
                },
            contentScale = ContentScale.Crop,
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
        IconButton(
            onClick = onWaitlistClick,
            modifier = Modifier
                .padding(RecipeTheme.paddings.allLarge)
                .align(Alignment.TopEnd)
                .size(40.dp)
                .background(color = RecipeTheme.colorScheme.background, shape = CircleShape)
                .border(1.dp, RecipeTheme.colorScheme.focusedBorder, CircleShape),
            colors = IconButtonDefaults.outlinedIconButtonColors(
                contentColor = RecipeTheme.colorScheme.primary
            )
        ) {
            Icon(
                painter = painterResource(
                    id = if (isAddedToWaitlist) R.drawable.heart_filled else R.drawable.heart_outlined
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
            )
        }
        Text(
            text = name,
            style = RecipeTheme.typography.headlineSmall,
            color = RecipeTheme.colorScheme.text2,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(RecipeTheme.paddings.allLarge)
        )
    }
}

@Preview
@Composable
fun RecipeImagePreview() {
    RecipeImage(
        name = "Paneer",
        imageUrl = "",
        isAddedToWaitlist = false,
        modifier = Modifier.fillMaxSize()
    ) {

    }
}