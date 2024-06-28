package me.ashutoshkk.recipeapp.presentation.ui.search.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    onClick: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_any_recipe),
                style = RecipeTheme.typography.bodyMedium,
                color = RecipeTheme.colorScheme.subText,
            )
        },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = RecipeTheme.colorScheme.textFieldBackground,
            unfocusedContainerColor = RecipeTheme.colorScheme.textFieldBackground,
            disabledContainerColor = RecipeTheme.colorScheme.textFieldBackground,
            errorContainerColor = RecipeTheme.colorScheme.textFieldBackground,
            focusedLabelColor = RecipeTheme.colorScheme.text2,
            unfocusedLabelColor = RecipeTheme.colorScheme.text2,
            disabledLabelColor = RecipeTheme.colorScheme.subText,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = RecipeTheme.paddings.horizontal)
            .clickable {
                Log.d("Ashu", "Click")
                onClick()
            },
        readOnly = readOnly,
        enabled = !readOnly
    )
}