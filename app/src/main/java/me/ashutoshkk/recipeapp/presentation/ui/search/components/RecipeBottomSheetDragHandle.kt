package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun RecipeBottomSheetDragHandle(
    name: String,
    onBackClick: () -> Unit,
    isAddedToWaitlist: Boolean,
    onWaitlistClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = RecipeTheme.paddings.horizontal,
                vertical = RecipeTheme.paddings.vertical
            ),
        horizontalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.horizontalSmall),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Text(
            text = name,
            style = RecipeTheme.typography.titleLarge,
            color = RecipeTheme.colorScheme.heading,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = onWaitlistClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = RecipeTheme.colorScheme.primary,
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
    }
}