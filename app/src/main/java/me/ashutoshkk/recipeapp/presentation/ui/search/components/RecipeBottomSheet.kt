package me.ashutoshkk.recipeapp.presentation.ui.search.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import me.ashutoshkk.recipeapp.domain.model.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBottomSheet(
    recipe: Recipe,
    onDismiss: () -> Unit,
) {
    val step by remember {
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

    }


}