package me.ashutoshkk.recipeapp.presentation.ui.recipe

import android.text.Html
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.common.Constants.IMAGE_URL
import me.ashutoshkk.recipeapp.domain.model.Ingredient
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.Ingredient
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.RecipeImage
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.RecipeInfo
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun RecipeScreen() {
    val viewModel: RecipeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalInBetween)
        ) {
            uiState.recipe?.let { recipe ->
                RecipeImage(
                    name = recipe.title,
                    imageUrl = recipe.image,
                    isAddedToWaitlist = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = RecipeTheme.paddings.horizontal),
                    horizontalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.horizontal),
                ) {
                    RecipeInfo(
                        title = stringResource(id = R.string.ready_in),
                        description = "${recipe.readyInMinutes} min",
                        modifier = Modifier.weight(1f)
                    )
                    RecipeInfo(
                        title = stringResource(id = R.string.servings),
                        description = recipe.servings.toString(),
                        modifier = Modifier.weight(1f)
                    )
                    RecipeInfo(
                        title = stringResource(id = R.string.price_serving),
                        description = recipe.pricePerServing.toString(),
                        modifier = Modifier.weight(1f)
                    )
                }
                Text(
                    text = stringResource(id = R.string.ingredients),
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
                    items(items = recipe.ingredients, key = { it.id }) {
                        Ingredient(it)
                    }
                }
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
                val equipments = remember {
                    listOf(
                        Ingredient(1, IMAGE_URL.plus("parsley.jpg"), "Equipment"),
                        Ingredient(2, IMAGE_URL.plus("parsley.jpg"), "Equipment"),
                        Ingredient(3, IMAGE_URL.plus("parsley.jpg"), "Equipment"),
                    )
                }
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = RecipeTheme.paddings.horizontal),
                    horizontalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.horizontal)
                ) {
                    items(items = equipments, key = { it.id }) {
                        Ingredient(it)
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
            }
        }
    }

}