package me.ashutoshkk.recipeapp.presentation.ui.home

import me.ashutoshkk.recipeapp.domain.model.RandomRecipe
import me.ashutoshkk.recipeapp.domain.model.Recipe

data class HomeUiState(
    val randomRecipe: List<RandomRecipe> = emptyList(),
    val isRandomRecipeLoading: Boolean = false,
    val allRecipe: List<Recipe> = emptyList(),
    val isAllRecipeLoading: Boolean = false,
    val error: String? = null
)