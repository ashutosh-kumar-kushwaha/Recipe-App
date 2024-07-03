package me.ashutoshkk.recipeapp.presentation.ui.recipe.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun ExpandableItem(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(RecipeTheme.colorScheme.background2, RoundedCornerShape(8.dp))
            .animateContentSize()
            .clickable {
                expanded = !expanded
            }
            .padding(
                horizontal = RecipeTheme.paddings.horizontal,
                vertical = RecipeTheme.paddings.verticalInBetween
            ),
        verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalInBetweenSmall)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = RecipeTheme.typography.titleSmall,
                color = RecipeTheme.colorScheme.text,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = if (expanded) R.drawable.up_arrow else R.drawable.down_arrow),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
            )
        }
        if (expanded) {
            Text(
                text = description,
                style = RecipeTheme.typography.bodyMedium,
                color = RecipeTheme.colorScheme.subText
            )
        }

    }
}