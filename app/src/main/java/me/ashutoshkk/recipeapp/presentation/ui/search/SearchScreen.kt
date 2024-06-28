package me.ashutoshkk.recipeapp.presentation.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.ashutoshkk.recipeapp.presentation.ui.home.components.ProgressBar
import me.ashutoshkk.recipeapp.presentation.ui.search.components.SearchRecipe
import me.ashutoshkk.recipeapp.presentation.ui.search.components.SearchTextField
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@Composable
fun SearchScreen(
    navigateTo: (String) -> Unit,
    navigateBack: () -> Boolean
) {
    val viewModel: SearchViewModel = hiltViewModel()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(vertical = RecipeTheme.paddings.verticalLarge)
        ) {
            SearchTextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                navigateBack()
                            }
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            viewModel.clearSearchText()
                        }
                    )
                }
            )
            if (uiState.isLoading) {
                ProgressBar()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = RecipeTheme.paddings.horizontal)
                ) {
                    items(uiState.recipes) {
                        SearchRecipe(it) {

                        }
                    }
                }
            }

            if (!uiState.error.isNullOrBlank()) {
                LaunchedEffect(Unit) {
                    snackbarHostState.showSnackbar(
                        message = uiState.error!!
                    )
                    viewModel.resetErrorMessage()
                }
            }
        }
    }
}