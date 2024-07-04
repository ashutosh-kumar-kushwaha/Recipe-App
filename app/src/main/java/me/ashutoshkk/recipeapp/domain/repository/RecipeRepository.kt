package me.ashutoshkk.recipeapp.domain.repository

import kotlinx.coroutines.flow.Flow
import me.ashutoshkk.recipeapp.data.remote.dto.RandomRecipeDto
import me.ashutoshkk.recipeapp.data.remote.dto.RecipeDetailsDto
import me.ashutoshkk.recipeapp.data.remote.dto.SearchRecipeDto
import me.ashutoshkk.recipeapp.data.room.FavoriteRecipe

interface RecipeRepository {

    suspend fun getRandomRecipe(): RandomRecipeDto

    suspend fun searchRecipe(query: String): SearchRecipeDto

    suspend fun getRecipeDetails(id: Int): RecipeDetailsDto

    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipe>>

    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe)

    suspend fun deleteFavoriteRecipe(recipeId: Int)

    fun isFavoriteRecipe(recipeId: Int): Flow<Boolean>

}