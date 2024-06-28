package me.ashutoshkk.recipeapp.data.remote.dto

import me.ashutoshkk.recipeapp.domain.model.Recipe

data class RecipeDto(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
)

fun RecipeDto.toRecipe() = Recipe(id, image, title)