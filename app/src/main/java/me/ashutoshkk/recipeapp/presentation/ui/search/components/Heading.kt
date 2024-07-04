package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun Heading(
    @StringRes name: Int,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(RecipeTheme.colorScheme.textFieldBackground)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = RecipeTheme.paddings.horizontal,
                    vertical = RecipeTheme.paddings.verticalSmall
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(name),
                style = RecipeTheme.typography.titleLarge,
                modifier = Modifier.weight(1f),
                color = RecipeTheme.colorScheme.text
            )
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}