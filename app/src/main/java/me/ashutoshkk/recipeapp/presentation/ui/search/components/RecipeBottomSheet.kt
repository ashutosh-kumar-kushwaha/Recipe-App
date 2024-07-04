package me.ashutoshkk.recipeapp.presentation.ui.search.components


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
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
        val visibleState1 =
            remember { MutableTransitionState(false).apply { targetState = true } }
        val visibleState2 =
            remember { MutableTransitionState(false).apply { targetState = false } }
        val visibleState3 =
            remember { MutableTransitionState(false).apply { targetState = false } }
        val visibleState4 =
            remember { MutableTransitionState(false).apply { targetState = false } }

        when (step) {
            Step.IMAGE -> {
                LaunchedEffect(Unit) {
                    visibleState2.targetState = false
                    visibleState3.targetState = false
                    visibleState4.targetState = false
                    if (back) delay(750)
                    visibleState1.targetState = true
                }
            }

            Step.INGREDIENTS -> {
                LaunchedEffect(Unit) {
                    visibleState1.targetState = false
                    visibleState3.targetState = false
                    visibleState4.targetState = false
                    if (back) delay(800)
                    visibleState2.targetState = true
                }
            }

            Step.FULL_RECIPE -> {
                LaunchedEffect(Unit) {
                    visibleState1.targetState = false
                    visibleState2.targetState = false
                    visibleState4.targetState = false
                    if (back) delay(750)
                    visibleState3.targetState = true
                }
            }

            Step.SIMILAR_RECIPE -> {
                LaunchedEffect(Unit) {
                    visibleState1.targetState = false
                    visibleState2.targetState = false
                    visibleState3.targetState = false
                    if (back) delay(700)
                    visibleState4.targetState = true
                }
            }
        }

        Column(
            modifier = Modifier
        ) {
            AnimatedVisibility(
                visibleState = visibleState1,
                enter = fadeIn(tween(1)),
                exit = fadeOut(tween(1))
            ) {
                ImageStep(recipe = recipe) {
                    back = false
                    step = Step.INGREDIENTS
                }
            }
            AnimatedVisibility(
                visibleState = visibleState2,
                enter = if (back) {
                    fadeIn(tween(1))
                } else {
                    slideInVertically(
                        tween(1500),
                        initialOffsetY = { 1980 }
                    )
                },
                exit = if (back) {
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
                visible = step == Step.FULL_RECIPE || step == Step.SIMILAR_RECIPE,
                enter = fadeIn(tween(1)),
                exit = fadeOut(tween(1500), targetAlpha = 0.99f)
            ) {
                Heading(R.string.ingredients) {
                    back = true
                    step = Step.IMAGE
                }
            }
            AnimatedVisibility(
                visibleState3,
                enter = if (back) {
                    fadeIn(tween(1))
                } else {
                    slideInVertically(
                        tween(1500),
                        initialOffsetY = { 1980 }
                    )
                },
                exit = if (back) {
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
                exit = fadeOut(tween(1500), targetAlpha = 0.99f)
            ) {
                Heading(R.string.full_recipe) {
                    back = true
                    step = Step.INGREDIENTS
                }
            }
            AnimatedVisibility(
                visibleState4,
                enter = if (back) {
                    fadeIn(tween(1))
                } else {
                    slideInVertically(
                        tween(1500),
                        initialOffsetY = { 1980 }
                    )
                },
                exit = if (back) {
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