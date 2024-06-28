package me.ashutoshkk.recipeapp.presentation.ui.home

import me.ashutoshkk.recipeapp.domain.model.Recipe
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails

data class HomeUiState (
    val randomRecipe: List<RecipeDetails> = emptyList(),
    val isRandomRecipeLoading: Boolean = false,
    val allRecipe: List<Recipe> = emptyList(),
    val isAllRecipeLoading: Boolean = false,
    val error: String? = null
)