package me.ashutoshkk.recipeapp.presentation.ui.search.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBottomSheet(
    recipe: RecipeDetails,
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
            if(step == Step.FULL_RECIPE || step == Step.SIMILAR_RECIPE){
                Heading(R.string.get_ingredients){
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
                    step = Step.SIMILAR_RECIPE
                }
            }
            if(step == Step.SIMILAR_RECIPE){
                Heading(R.string.get_full_recipe){
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
                FullRecipeStep(
                    recipe,
                    onBackClick = {
                        step = Step.INGREDIENTS
                    }
                ) {
                    step = Step.SIMILAR_RECIPE
                }
            }
        }


    }


}