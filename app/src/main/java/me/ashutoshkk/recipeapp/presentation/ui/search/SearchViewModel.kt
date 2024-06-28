package me.ashutoshkk.recipeapp.presentation.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import me.ashutoshkk.recipeapp.common.Resource
import me.ashutoshkk.recipeapp.domain.useCase.SearchRecipeUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchRecipe: SearchRecipeUseCase
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

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

    fun resetErrorMessage() {
        _uiState.update { it.copy(error = null) }
    }

    fun clearSearchText() {
        _searchText.value = ""
    }
}