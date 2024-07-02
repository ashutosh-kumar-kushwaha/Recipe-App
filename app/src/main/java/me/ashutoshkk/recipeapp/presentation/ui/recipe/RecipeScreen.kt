package me.ashutoshkk.recipeapp.presentation.ui.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.ashutoshkk.recipeapp.presentation.ui.recipe.components.RecipeImage

@Composable
fun RecipeScreen() {
    val viewModel: RecipeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(rememberScrollState())
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
            }
        }
    }

}