package me.ashutoshkk.recipeapp.presentation.ui.search.components

import me.ashutoshkk.recipeapp.domain.model.Recipe
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails

data class RecipeUiState (
    val recipe: RecipeDetails? = null,
    val similarRecipe: List<Recipe> = emptyList()
)