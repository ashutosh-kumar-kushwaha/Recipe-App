package me.ashutoshkk.recipeapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails

@Entity(tableName = "favorite_recipe")
data class FavoriteRecipe(
    @PrimaryKey val id: Int,
    val image: String,
    val title: String,
    val readyInMinutes: Int
)

fun RecipeDetails.toFavoriteRecipe() = FavoriteRecipe(
    id = id,
    image = image,
    title = title,
    readyInMinutes = readyInMinutes
)
