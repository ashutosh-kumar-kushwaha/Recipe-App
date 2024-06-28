package me.ashutoshkk.recipeapp.data.remote

import me.ashutoshkk.recipeapp.common.Constants.API_KEY
import me.ashutoshkk.recipeapp.data.remote.dto.RandomRecipeDto
import me.ashutoshkk.recipeapp.data.remote.dto.SearchRecipeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApiService {

    @GET("complexSearch")
    suspend fun searchRecipe(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String = API_KEY,
    ): SearchRecipeDto

    @GET("recipes/random")
    suspend fun getRandomRecipe(
        @Query("number") number: Int = 10,
        @Query("apiKey") apiKey: String = API_KEY,
    ): RandomRecipeDto

}