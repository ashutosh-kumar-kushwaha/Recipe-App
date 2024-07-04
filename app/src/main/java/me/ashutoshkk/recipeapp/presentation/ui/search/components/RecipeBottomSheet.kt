package me.ashutoshkk.recipeapp.presentation.ui.search.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.domain.model.Recipe
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBottomSheet(
    recipe: RecipeDetails,
    similarRecipe: List<Recipe>,
    isFavorite: Boolean = false,
    toggleFavorite: () -> Unit,
    fetchSimilarRecipe: () -> Unit,
    onDismiss: () -> Unit,
) {
    var step by remember {
        mutableStateOf(Step.IMAGE)
    }
    var back by remember {
        mutableStateOf(false)
    }
    val imageStepState =
        remember { MutableTransitionState(false).apply { targetState = true } }
    val ingredientsStepState =
        remember { MutableTransitionState(false).apply { targetState = false } }
    val recipeStepState =
        remember { MutableTransitionState(false).apply { targetState = false } }
    val similarStepState =
        remember { MutableTransitionState(false).apply { targetState = false } }
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(0.76f),
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        ),
        onDismissRequest = onDismiss,
        dragHandle = {
            RecipeBottomSheetDragHandle(
                name = recipe.title,
                isFavorite = isFavorite,
                toggleFavorite = toggleFavorite
            ) {
                back = true
                when (step) {
                    Step.IMAGE -> {}
                    Step.INGREDIENTS -> {
                        scope.launch {
                            step = Step.IMAGE
                            ingredientsStepState.targetState = false
                            delay(750)
                            imageStepState.targetState = true
                        }
                    }

                    Step.FULL_RECIPE -> {
                        scope.launch {
                            step = Step.INGREDIENTS
                            recipeStepState.targetState = false
                            delay(800)
                            ingredientsStepState.targetState = true
                        }
                    }

                    Step.SIMILAR_RECIPE -> {
                        scope.launch {
                            step = Step.FULL_RECIPE
                            similarStepState.targetState = false
                            delay(700)
                            recipeStepState.targetState = true
                        }
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
        ) {
            AnimatedVisibility(
                visibleState = imageStepState,
                enter = fadeIn(tween(1)),
                exit = fadeOut(tween(1))
            ) {
                ImageStep(recipe = recipe) {
                    back = false
                    step = Step.INGREDIENTS
                    imageStepState.targetState = false
                    ingredientsStepState.targetState = true
                }
            }
            AnimatedVisibility(
                visibleState = ingredientsStepState,
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
                        scope.launch {
                            back = true
                            step = Step.IMAGE
                            ingredientsStepState.targetState = false
                            delay(750)
                            imageStepState.targetState = true
                        }
                    }
                ) {
                    back = false
                    step = Step.FULL_RECIPE
                    ingredientsStepState.targetState = false
                    recipeStepState.targetState = true
                }
            }
            AnimatedVisibility(
                visible = step == Step.FULL_RECIPE || step == Step.SIMILAR_RECIPE,
                enter = fadeIn(tween(1)),
                exit = fadeOut(tween(1500), targetAlpha = 0.99f)
            ) {
                var offset by remember { mutableStateOf(IntOffset(0, 0)) }
                Spacer(modifier = Modifier.height(offset.y.dp))
                Heading(
                    name = R.string.ingredients,
                    onBackClick = {
                        scope.launch {
                            back = true
                            step = Step.FULL_RECIPE
                            similarStepState.targetState = false
                            delay(700)
                            recipeStepState.targetState = true
                            delay(100)
                            step = Step.INGREDIENTS
                            recipeStepState.targetState = false
                            delay(800)
                            ingredientsStepState.targetState = true
                            delay(100)
                            ingredientsStepState.targetState = false
                            step = Step.IMAGE
                            delay(750)
                            imageStepState.targetState = true
                        }
                    },
                    offset = offset
                ) {
                    offset = it
                }
            }
            AnimatedVisibility(
                recipeStepState,
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
                        scope.launch {
                            back = true
                            step = Step.INGREDIENTS
                            recipeStepState.targetState = false
                            delay(800)
                            ingredientsStepState.targetState = true
                        }
                    }
                ) {
                    back = false
                    fetchSimilarRecipe()
                    step = Step.SIMILAR_RECIPE
                    recipeStepState.targetState = false
                    similarStepState.targetState = true
                }
            }
            AnimatedVisibility(
                visible = step == Step.SIMILAR_RECIPE,
                enter = fadeIn(tween(1)),
                exit = fadeOut(tween(1500), targetAlpha = 0.99f)
            ) {
                var offset by remember { mutableStateOf(IntOffset(0, 0)) }
                Spacer(modifier = Modifier.height(offset.y.dp))
                Heading(
                    name = R.string.full_recipe,
                    onBackClick = {
                        scope.launch {
                            back = true
                            similarStepState.targetState = false
                            step = Step.FULL_RECIPE
                            delay(750)
                            recipeStepState.targetState = true
                            delay(100)
                            recipeStepState.targetState = false
                            step = Step.INGREDIENTS
                            delay(800)
                            ingredientsStepState.targetState = true
                        }
                    },
                    offset = offset
                ) {
                    offset = it
                }
            }
            AnimatedVisibility(
                similarStepState,
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
                    scope.launch {
                        back = true
                        step = Step.FULL_RECIPE
                        similarStepState.targetState = false
                        delay(700)
                        recipeStepState.targetState = true
                    }
                }

            }
        }
    }


}