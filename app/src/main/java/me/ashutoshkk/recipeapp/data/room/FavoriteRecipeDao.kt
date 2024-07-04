package me.ashutoshkk.recipeapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteRecipeDao {

    @Query("SELECT * FROM favorite_recipe")
    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipe>>

    @Query("DELETE FROM favorite_recipe WHERE id = :recipeId")
    suspend fun delete(recipeId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: FavoriteRecipe)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_recipe WHERE id = :recipeId)")
    fun isFavoriteRecipe(recipeId: Int): Flow<Boolean>

}