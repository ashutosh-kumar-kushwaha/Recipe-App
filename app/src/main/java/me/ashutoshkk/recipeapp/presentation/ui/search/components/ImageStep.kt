package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.RecipeInfo
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun ImageStep(
    recipe: RecipeDetails,
    onGetIngredientClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = RecipeTheme.paddings.verticalLarge),
            verticalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.verticalInBetween)
        ) {
            val painter = rememberAsyncImagePainter(model = recipe.image)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
            )
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

        }
        Button(
            text = R.string.get_ingredients,
            onClick = onGetIngredientClick
        )
    }
}