package me.ashutoshkk.recipeapp.data.remote.dto

import me.ashutoshkk.recipeapp.domain.model.Ingredient

data class ExtendedIngredient(
    val aisle: String,
    val amount: Double,
    val consistency: String,
    val id: Int,
    val image: String,
    val measures: Measures,
    val meta: List<String>,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalName: String,
    val unit: String
)

fun ExtendedIngredient.toIngredient() = Ingredient(id, image, nameClean)