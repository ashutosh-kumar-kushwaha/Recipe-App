package me.ashutoshkk.recipeapp.data.remote.dto

data class SearchRecipeDto(
    val number: Int,
    val offset: Int,
    val results: List<RecipeDto>,
    val totalResults: Int
)