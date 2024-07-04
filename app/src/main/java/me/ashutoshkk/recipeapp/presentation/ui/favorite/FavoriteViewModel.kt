package me.ashutoshkk.recipeapp.presentation.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.ashutoshkk.recipeapp.data.room.FavoriteRecipe
import me.ashutoshkk.recipeapp.domain.useCase.RecipeUseCase
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCase: RecipeUseCase
) : ViewModel() {

    private val _favoriteRecipe = MutableStateFlow(emptyList<FavoriteRecipe>())
    val favoriteRecipe = _favoriteRecipe.asStateFlow()

    init {
        getAllFavoriteRecipes()
    }

    private fun getAllFavoriteRecipes() {
        useCase.getAllFavoriteRecipes().onEach {
            _favoriteRecipe.value = it
        }.launchIn(viewModelScope)
    }

}