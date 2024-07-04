package me.ashutoshkk.recipeapp.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.ashutoshkk.recipeapp.common.Resource
import me.ashutoshkk.recipeapp.data.room.toFavoriteRecipe
import me.ashutoshkk.recipeapp.domain.useCase.RecipeUseCase
import me.ashutoshkk.recipeapp.domain.useCase.SearchRecipeUseCase
import me.ashutoshkk.recipeapp.presentation.ui.search.components.RecipeUiState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchRecipe: SearchRecipeUseCase,
    private val recipeUseCase: RecipeUseCase
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private val _recipeUiState = MutableStateFlow(RecipeUiState())
    val recipeUiState = _recipeUiState.asStateFlow()

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet = _showBottomSheet.asStateFlow()

    init {
        handleSearchQuery()
    }

    @OptIn(FlowPreview::class)
    private fun handleSearchQuery() {
        _searchText
            .debounce(500L)
            .filter { it.isNotBlank() }
            .onEach {
                _uiState.update { it.copy(isLoading = true) }
                getSearchRecipe(it).onEach { response ->
                    when (response) {
                        is Resource.Success -> {

                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    recipes = response.data!!
                                )
                            }
                        }

                        is Resource.Error -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    error = response.message!!
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _uiState.update { it.copy(isLoading = true) }
                        }
                    }
                }.launchIn(viewModelScope)
            }.launchIn(viewModelScope)
    }

    fun fetchRecipe(recipeId: Int) {
        recipeUseCase.getDetails(recipeId).onEach { response ->
            when (response) {
                is Resource.Loading -> {}

                is Resource.Success -> {
                    _recipeUiState.value = RecipeUiState(
                        recipe = response.data!!,
                        isFavorite = recipeUseCase.isFavoriteRecipe(recipeId).first()
                    )
                    _showBottomSheet.value = true
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(error = response.message) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if (recipeUiState.value.isFavorite) {
                recipeUiState.value.recipe?.let {
                    recipeUseCase.deleteFavoriteRecipe(it.id)
                }
            } else {
                recipeUiState.value.recipe?.let { recipeUseCase.insertFavoriteRecipe(it.toFavoriteRecipe()) }
            }
        }
    }

    fun resetErrorMessage() {
        _uiState.update { it.copy(error = null) }
    }

    fun clearSearchText() {
        _searchText.value = ""
    }

    fun hideBottomSheet() {
        _showBottomSheet.value = false
    }

    fun fetchSimilarRecipe() {
        if (recipeUiState.value.similarRecipe.isEmpty()) {
            getSearchRecipe().onEach { response ->
                when (response) {
                    is Resource.Loading -> {}

                    is Resource.Success -> {
                        _recipeUiState.update { it.copy(similarRecipe = response.data!!) }
                    }

                    is Resource.Error -> {
                        _uiState.update { it.copy(error = response.message) }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}