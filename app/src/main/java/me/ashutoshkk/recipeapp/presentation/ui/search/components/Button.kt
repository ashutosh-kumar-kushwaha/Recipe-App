package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun Button(
    @StringRes text: Int,
    onClick: () -> Unit
) {
    androidx.compose.material3.Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = RecipeTheme.paddings.horizontal)
            .height(48.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = RecipeTheme.colorScheme.primary,
            contentColor = RecipeTheme.colorScheme.text2
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = stringResource(id = text),
            style = RecipeTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.width(RecipeTheme.paddings.horizontalSmall))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
}