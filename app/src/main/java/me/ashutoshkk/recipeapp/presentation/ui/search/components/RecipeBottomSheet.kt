package me.ashutoshkk.recipeapp.presentation.ui.search.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.domain.model.Recipe
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBottomSheet(
    recipe: RecipeDetails,
    similarRecipe: List<Recipe>,
    fetchSimilarRecipe: () -> Unit,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(0.78f),
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        ),
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
        var step by remember {
            mutableStateOf(Step.IMAGE)
        }
        Column(
            modifier = Modifier

        ) {
            AnimatedVisibility(
                visible = step == Step.IMAGE,
            ) {
                ImageStep(recipe = recipe) {
                    step = Step.INGREDIENTS
                }
            }
            AnimatedVisibility(
                visible = step == Step.INGREDIENTS,
                enter = slideInVertically(
                    tween(1500),
                    initialOffsetY = { 1980 }
                )
            ) {
                IngredientsStep(
                    recipe.ingredients,
                    onBackClick = {
                        step = Step.IMAGE
                    }
                ) {
                    step = Step.FULL_RECIPE
                }
            }
            if (step == Step.FULL_RECIPE || step == Step.SIMILAR_RECIPE) {
                Heading(R.string.ingredients) {
                    step = Step.IMAGE
                }
            }
            AnimatedVisibility(
                visible = step == Step.FULL_RECIPE,
                enter = slideInVertically(
                    tween(1500),
                    initialOffsetY = { 1980 }
                )
            ) {
                FullRecipeStep(
                    recipe,
                    onBackClick = {
                        step = Step.INGREDIENTS
                    }
                ) {
                    fetchSimilarRecipe()
                    step = Step.SIMILAR_RECIPE
                }
            }
            if (step == Step.SIMILAR_RECIPE) {
                Heading(R.string.full_recipe) {
                    step = Step.INGREDIENTS
                }
            }
            AnimatedVisibility(
                visible = step == Step.SIMILAR_RECIPE,
                enter = slideInVertically(
                    tween(1500),
                    initialOffsetY = { 1980 }
                )
            ) {
                SimilarRecipeStep(
                    similarRecipe,
                ) {
                    step = Step.FULL_RECIPE
                }

            }
        }


    }


}