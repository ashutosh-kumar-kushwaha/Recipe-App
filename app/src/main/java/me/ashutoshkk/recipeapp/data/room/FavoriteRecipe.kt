package me.ashutoshkk.recipeapp.data.room

import androidx.room.Entity

@Entity(tableName = "favorite_recipe")
data class FavoriteRecipe(
    val id: Int,
    val image: String,
    val title: String,
    val readyInMinutes: Int
)
