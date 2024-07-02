package me.ashutoshkk.recipeapp.domain.useCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ashutoshkk.recipeapp.common.Resource
import me.ashutoshkk.recipeapp.data.remote.dto.toRandomRecipe
import me.ashutoshkk.recipeapp.domain.model.RandomRecipe
import me.ashutoshkk.recipeapp.domain.repository.RecipeRepository
import retrofit2.HttpException
import javax.inject.Inject

class RandomRecipeUseCase @Inject constructor(private val repository: RecipeRepository) {

    operator fun invoke(): Flow<Resource<List<RandomRecipe>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getRandomRecipe().recipes.map { it.toRandomRecipe() }))
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

}