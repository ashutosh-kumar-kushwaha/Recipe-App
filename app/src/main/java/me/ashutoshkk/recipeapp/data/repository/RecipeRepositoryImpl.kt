package me.ashutoshkk.recipeapp.data.repository

import me.ashutoshkk.recipeapp.data.remote.SpoonacularApiService
import me.ashutoshkk.recipeapp.data.remote.dto.RandomRecipeDto
import me.ashutoshkk.recipeapp.data.remote.dto.SearchRecipeDto
import me.ashutoshkk.recipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val spoonacularApiService: SpoonacularApiService
): RecipeRepository {
    override suspend fun getRandomRecipe(): RandomRecipeDto = spoonacularApiService.getRandomRecipe()

    override suspend fun searchRecipe(query: String): SearchRecipeDto = spoonacularApiService.searchRecipe(query)
}