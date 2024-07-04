package me.ashutoshkk.recipeapp.presentation.ui.recipe.components

import android.text.Html
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun OtherRecipeDetails(recipe: RecipeDetails) {
    Text(
        text = stringResource(id = R.string.instructions),
        style = RecipeTheme.typography.titleSmall,
        color = RecipeTheme.colorScheme.text,
        modifier = Modifier.padding(
            horizontal = RecipeTheme.paddings.horizontal
        ),
        fontWeight = FontWeight.Bold
    )
    Text(
        text = Html.fromHtml(recipe.instructions, Html.FROM_HTML_MODE_LEGACY)
            .toString(),
        style = RecipeTheme.typography.bodyMedium,
        color = RecipeTheme.colorScheme.subText,
        modifier = Modifier.padding(
            horizontal = RecipeTheme.paddings.horizontal
        )
    )
    Text(
        text = stringResource(id = R.string.equipments),
        style = RecipeTheme.typography.titleSmall,
        color = RecipeTheme.colorScheme.text,
        modifier = Modifier.padding(
            horizontal = RecipeTheme.paddings.horizontal
        ),
        fontWeight = FontWeight.Bold
    )
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = RecipeTheme.paddings.horizontal),
        horizontalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.horizontal)
    ) {
        items(
            items = recipe.equipments,
            key = {
                it.id
            }
        ) {
            Ingredient(it.name, it.image)
        }
    }
    Text(
        text = stringResource(id = R.string.quick_summary),
        style = RecipeTheme.typography.titleSmall,
        color = RecipeTheme.colorScheme.text,
        modifier = Modifier.padding(
            horizontal = RecipeTheme.paddings.horizontal
        ),
        fontWeight = FontWeight.Bold
    )
    Text(
        text = Html.fromHtml(recipe.summary, Html.FROM_HTML_MODE_LEGACY)
            .toString(),
        style = RecipeTheme.typography.bodyMedium,
        color = RecipeTheme.colorScheme.subText,
        modifier = Modifier.padding(
            horizontal = RecipeTheme.paddings.horizontal
        )
    )
    val info = remember {
        listOf(
            R.string.nutrition to R.string.lorem_ipsum,
            R.string.bad_for_health_nutrition to R.string.lorem_ipsum,
            R.string.good_for_health_nutrition to R.string.lorem_ipsum,
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = RecipeTheme.paddings.verticalLarge),
        verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalSmall)
    ) {
        info.forEach {
            ExpandableItem(
                title = stringResource(id = it.first),
                description = stringResource(id = it.second),
                modifier = Modifier.padding(
                    horizontal = RecipeTheme.paddings.horizontal,
                )
            )
        }
    }
}