package me.ashutoshkk.recipeapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.ashutoshkk.recipeapp.data.remote.SpoonacularApiService
import me.ashutoshkk.recipeapp.data.remote.dto.RandomRecipeDto
import me.ashutoshkk.recipeapp.data.remote.dto.RecipeDetailsDto
import me.ashutoshkk.recipeapp.data.remote.dto.SearchRecipeDto
import me.ashutoshkk.recipeapp.data.room.FavoriteRecipe
import me.ashutoshkk.recipeapp.data.room.FavoriteRecipeDao
import me.ashutoshkk.recipeapp.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val apiService: SpoonacularApiService,
    private val favoriteRecipeDao: FavoriteRecipeDao
) : RecipeRepository {

    override suspend fun getRandomRecipe(): RandomRecipeDto = withContext(Dispatchers.IO) {
        apiService.getRandomRecipe()
    }

    override suspend fun searchRecipe(query: String): SearchRecipeDto =
        withContext(Dispatchers.IO) {
            apiService.searchRecipe(query)
        }

    override suspend fun getRecipeDetails(id: Int): RecipeDetailsDto = withContext(Dispatchers.IO) {
        apiService.getRecipeDetails(id)
    }

    override fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipe>> =
        favoriteRecipeDao.getAllFavoriteRecipes()

    override suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe) =
        withContext(Dispatchers.IO) {
            favoriteRecipeDao.insert(recipe)
        }

    override suspend fun deleteFavoriteRecipe(recipeId: Int) = withContext(Dispatchers.IO) {
        favoriteRecipeDao.delete(recipeId)
    }

    override fun isFavoriteRecipe(recipeId: Int) =
        favoriteRecipeDao.isFavoriteRecipe(recipeId)

}