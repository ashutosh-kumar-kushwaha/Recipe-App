package me.ashutoshkk.recipeapp.presentation.ui.recipe

import me.ashutoshkk.recipeapp.domain.model.RecipeDetails

data class RecipeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val recipe: RecipeDetails? = null
)
