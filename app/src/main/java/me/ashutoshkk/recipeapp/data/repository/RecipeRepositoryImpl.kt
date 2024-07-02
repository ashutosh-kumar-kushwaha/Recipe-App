package me.ashutoshkk.recipeapp.data.repository

import me.ashutoshkk.recipeapp.data.remote.SpoonacularApiService
import me.ashutoshkk.recipeapp.data.remote.dto.RandomRecipeDto
import me.ashutoshkk.recipeapp.data.remote.dto.RecipeDetailsDto
import me.ashutoshkk.recipeapp.data.remote.dto.SearchRecipeDto
import me.ashutoshkk.recipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val apiService: SpoonacularApiService
): RecipeRepository {
    override suspend fun getRandomRecipe(): RandomRecipeDto = apiService.getRandomRecipe()

    override suspend fun searchRecipe(query: String): SearchRecipeDto = apiService.searchRecipe(query)

    override suspend fun getRecipeDetails(id: Int): RecipeDetailsDto = apiService.getRecipeDetails(id)
}