package me.ashutoshkk.recipeapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteRecipe::class],
    version = 1
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract val favoriteRecipeDao: FavoriteRecipeDao
}