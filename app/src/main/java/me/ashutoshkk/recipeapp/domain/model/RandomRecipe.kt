package me.ashutoshkk.recipeapp.domain.model

data class RandomRecipe(
    val id: Int,
    val image: String,
    val title: String,
    val readyInMinutes: Int
)
