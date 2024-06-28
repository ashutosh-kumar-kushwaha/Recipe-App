package me.ashutoshkk.recipeapp.presentation.ui.search

import me.ashutoshkk.recipeapp.domain.model.Recipe

data class SearchUiState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val recipes: List<Recipe> = emptyList()
)