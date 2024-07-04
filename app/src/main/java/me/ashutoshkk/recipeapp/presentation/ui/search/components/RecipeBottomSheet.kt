package me.ashutoshkk.recipeapp.presentation.ui.search.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
    var step by remember {
        mutableStateOf(Step.IMAGE)
    }
    var back by remember {
        mutableStateOf(false)
    }
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
                    back = true
                    when (step) {
                        Step.IMAGE -> {}
                        Step.INGREDIENTS -> step = Step.IMAGE
                        Step.FULL_RECIPE -> step = Step.INGREDIENTS
                        Step.SIMILAR_RECIPE -> step = Step.FULL_RECIPE
                    }
                }
            ) {

            }
        }
    ) {
        Column(
            modifier = Modifier
        ) {
            AnimatedVisibility(
                visible = step == Step.IMAGE,
            ) {
                ImageStep(recipe = recipe) {
                    back = false
                    step = Step.INGREDIENTS
                }
            }
            AnimatedVisibility(
                visible = step == Step.INGREDIENTS,
                enter = if (back) {
                    fadeIn(tween(1))
                } else {
                    slideInVertically(
                        tween(1500),
                        initialOffsetY = { 1980 }
                    )
                },
                exit = if(back) {
                    slideOutVertically(
                        tween(1500),
                        targetOffsetY = { 1980 }
                    )
                } else {
                    fadeOut(tween(1))
                }
            ) {
                IngredientsStep(
                    recipe.ingredients,
                    onBackClick = {
                        back = true
                        step = Step.IMAGE
                    }
                ) {
                    back = false
                    step = Step.FULL_RECIPE
                }
            }
            AnimatedVisibility(
                step == Step.FULL_RECIPE || step == Step.SIMILAR_RECIPE,
                enter = fadeIn(tween(1)),
                exit = if(back) {
                    slideOutVertically(
                        tween(1500),
                        targetOffsetY = { 1980 }
                    )
                } else {
                    fadeOut(tween(1))
                }
            ) {
                Heading(R.string.ingredients) {
                    back = true
                    step = Step.IMAGE
                }
            }
            AnimatedVisibility(
                visible = step == Step.FULL_RECIPE,
                enter = if (back) {
                    fadeIn(tween(1))
                } else {
                    slideInVertically(
                        tween(1500),
                        initialOffsetY = { 1980 }
                    )
                },
                exit = if(back) {
                    slideOutVertically(
                        tween(1500),
                        targetOffsetY = { 1980 }
                    )
                } else {
                    fadeOut(tween(1))
                }
            ) {
                FullRecipeStep(
                    recipe,
                    onBackClick = {
                        back = true
                        step = Step.INGREDIENTS
                    }
                ) {
                    back = false
                    fetchSimilarRecipe()
                    step = Step.SIMILAR_RECIPE
                }
            }
            AnimatedVisibility(
                visible = step == Step.SIMILAR_RECIPE,
                enter = fadeIn(tween(1)),
                exit = fadeOut(tween(1))
            ) {
                Heading(R.string.full_recipe) {
                    back = true
                    step = Step.INGREDIENTS
                }
            }
            AnimatedVisibility(
                visible = step == Step.SIMILAR_RECIPE,
                enter = if (back) {
                    fadeIn(tween(1))
                } else {
                    slideInVertically(
                        tween(1500),
                        initialOffsetY = { 1980 }
                    )
                },
                exit = if(back) {
                    slideOutVertically(
                        tween(1500),
                        targetOffsetY = { 1980 }
                    )
                } else {
                    fadeOut(tween(1))
                }
            ) {
                SimilarRecipeStep(
                    similarRecipe,
                ) {
                    back = true
                    step = Step.FULL_RECIPE
                }

            }
        }


    }


}