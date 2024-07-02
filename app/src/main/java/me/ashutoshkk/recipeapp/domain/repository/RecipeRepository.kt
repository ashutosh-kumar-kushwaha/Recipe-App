package me.ashutoshkk.recipeapp.domain.repository

import me.ashutoshkk.recipeapp.data.remote.dto.RandomRecipeDto
import me.ashutoshkk.recipeapp.data.remote.dto.RecipeDetailsDto
import me.ashutoshkk.recipeapp.data.remote.dto.SearchRecipeDto

interface RecipeRepository {

    suspend fun getRandomRecipe(): RandomRecipeDto

    suspend fun searchRecipe(query: String): SearchRecipeDto

    suspend fun getRecipeDetails(id: Int): RecipeDetailsDto

}