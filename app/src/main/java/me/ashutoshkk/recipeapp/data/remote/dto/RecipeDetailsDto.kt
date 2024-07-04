package me.ashutoshkk.recipeapp.data.remote.dto

import me.ashutoshkk.recipeapp.domain.model.RandomRecipe
import me.ashutoshkk.recipeapp.domain.model.RecipeDetails

data class RecipeDetailsDto(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cheap: Boolean,
    val cookingMinutes: Any? = null,
    val creditsText: String,
    val cuisines: List<Any>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Int,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val license: String = "",
    val lowFodmap: Boolean,
    val occasions: List<Any>,
    val originalId: Any? = null,
    val preparationMinutes: Any? = null,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int
)

fun RecipeDetailsDto.toRecipeDetails() = RecipeDetails(
    analyzedInstructions = this.analyzedInstructions,
    equipments = this.analyzedInstructions.getEquipments(),
    ingredients = this.extendedIngredients.map { it.toIngredient() },
    id = this.id,
    image = this.image,
    instructions = this.instructions,
    pricePerServing = this.pricePerServing,
    readyInMinutes = this.readyInMinutes,
    servings = this.servings,
    summary = this.summary,
    title = this.title,
)

fun RecipeDetailsDto.toRandomRecipe() = RandomRecipe(id, image, title, readyInMinutes)