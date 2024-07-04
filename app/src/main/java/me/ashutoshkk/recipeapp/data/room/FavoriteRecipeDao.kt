package me.ashutoshkk.recipeapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteRecipeDao {

    @Query("SELECT * FROM favorite_recipe")
    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipe>>

    @Query("DELETE FROM favorite_recipe WHERE id = :recipeId")
    suspend fun delete(recipeId: Int)

    @Insert
    suspend fun insert(recipe: FavoriteRecipe)

}