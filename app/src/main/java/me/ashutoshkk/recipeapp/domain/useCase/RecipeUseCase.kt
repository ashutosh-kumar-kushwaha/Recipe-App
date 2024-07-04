package me.ashutoshkk.recipeapp.domain.useCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ashutoshkk.recipeapp.common.Resource
import me.ashutoshkk.recipeapp.data.remote.dto.toRecipeDetails
import me.ashutoshkk.recipeapp.data.room.FavoriteRecipe
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails
import me.ashutoshkk.recipeapp.domain.repository.RecipeRepository
import retrofit2.HttpException
import javax.inject.Inject

class RecipeUseCase @Inject constructor(private val repository: RecipeRepository) {

    fun getDetails(id: Int): Flow<Resource<RecipeDetails>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getRecipeDetails(id).toRecipeDetails()))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred\nResponse code: ${e.code()}"
                )
            )
            e.printStackTrace()
        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach the server\nCheck your internet connection"))
            e.printStackTrace()
        }
    }

    suspend fun getAllFavoriteRecipes() = repository.getAllFavoriteRecipes()

    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe) =
        repository.insertFavoriteRecipe(recipe)

    suspend fun deleteFavoriteRecipe(recipeId: Int) = repository.deleteFavoriteRecipe(recipeId)

    fun isFavoriteRecipe(recipeId: Int) = repository.isFavoriteRecipe(recipeId)

}