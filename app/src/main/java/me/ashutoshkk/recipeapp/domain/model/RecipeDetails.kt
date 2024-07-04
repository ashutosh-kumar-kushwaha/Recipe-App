package me.ashutoshkk.recipeapp.domain.model

import me.ashutoshkk.recipeapp.data.remote.dto.AnalyzedInstruction
import me.ashutoshkk.recipeapp.data.remote.dto.Equipment
import me.ashutoshkk.recipeapp.data.remote.dto.ExtendedIngredient

data class RecipeDetails(
    val analyzedInstructions: List<AnalyzedInstruction>,
    val equipments: List<Equipment>,
    val ingredients: List<Ingredient>,
    val id: Int,
    val image: String,
    val instructions: String,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val summary: String,
    val title: String,
)