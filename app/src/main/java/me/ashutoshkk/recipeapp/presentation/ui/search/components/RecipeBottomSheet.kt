package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.RecipeInfo
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBottomSheet(
    recipe: RecipeDetails,
    onDismiss: () -> Unit,
) {
    var step by remember {
        mutableStateOf(Step.IMAGE)
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        dragHandle = {
            RecipeBottomSheetDragHandle(
                name = recipe.title,
                isAddedToWaitlist = false,
                onBackClick = {

                }
            ) {

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(bottom = RecipeTheme.paddings.verticalLarge)
                .verticalScroll(rememberScrollState()),
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
            Button(
                onClick = {
                    step = Step.INGREDIENTS
                },
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
                    text = stringResource(id = R.string.get_ingredients),
                    style = RecipeTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.width(RecipeTheme.paddings.horizontalSmall))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }

    }


}