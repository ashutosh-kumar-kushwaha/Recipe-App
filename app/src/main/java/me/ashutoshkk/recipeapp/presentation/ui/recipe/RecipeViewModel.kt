package me.ashutoshkk.recipeapp.presentation.ui.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import me.ashutoshkk.recipeapp.common.Resource
import me.ashutoshkk.recipeapp.domain.useCase.RecipeUseCase
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRecipeDetails: RecipeUseCase
) : ViewModel() {

    private val recipeId: Int = savedStateHandle["recipeId"]!!

    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchRecipeDetails()
    }

    private fun fetchRecipeDetails() {
        getRecipeDetails(recipeId).onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            recipe = response.data!!
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetErrorMessage() {
        _uiState.update { it.copy(error = null) }
    }

}