package me.ashutoshkk.recipeapp.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme
import kotlin.random.Random

@Composable
fun RecipeCard(
    title: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    readyInMinutes: Int = -1,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = RecipeTheme.colorScheme.border,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberAsyncImagePainter(model = imageUrl)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(
                    start = RecipeTheme.paddings.horizontal,
                    top = RecipeTheme.paddings.vertical,
                    bottom = RecipeTheme.paddings.vertical,
                    end = RecipeTheme.paddings.horizontalSmall
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = RecipeTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(RecipeTheme.paddings.verticalSmall))
            val ready = remember {
                if (readyInMinutes == -1) Random.nextInt(20, 59) else readyInMinutes
            }
            Text(
                text = "Ready in $ready min",
                style = RecipeTheme.typography.labelLarge,
                color = RecipeTheme.colorScheme.subText
            )
        }
    }
}