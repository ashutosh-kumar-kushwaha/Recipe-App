package me.ashutoshkk.recipeapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import me.ashutoshkk.recipeapp.common.Resource
import me.ashutoshkk.recipeapp.domain.useCase.RandomRecipeUseCase
import me.ashutoshkk.recipeapp.domain.useCase.SearchRecipeUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllRecipeUseCase: SearchRecipeUseCase,
    private val getRandomRecipeUseCase: RandomRecipeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getRandomRecipe()
        getAllRecipe()
    }

    private fun getRandomRecipe() {
        getRandomRecipeUseCase().onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isRandomRecipeLoading = true) }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isRandomRecipeLoading = false,
                            randomRecipe = response.data!!
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isRandomRecipeLoading = false,
                            error = response.message
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllRecipe() {
        getAllRecipeUseCase().onEach { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isAllRecipeLoading = true) }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isAllRecipeLoading = false,
                            allRecipe = response.data!!
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isAllRecipeLoading = false,
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