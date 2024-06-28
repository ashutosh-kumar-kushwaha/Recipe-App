package me.ashutoshkk.recipeapp.presentation.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.ashutoshkk.recipeapp.R
import me.ashutoshkk.recipeapp.presentation.ui.home.components.PopularRecipe
import me.ashutoshkk.recipeapp.presentation.ui.home.components.RecipeCard
import me.ashutoshkk.recipeapp.presentation.ui.theme.RecipeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateTo: (String) -> Unit) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = it.calculateStartPadding(LayoutDirection.Ltr),
                end = it.calculateEndPadding(LayoutDirection.Ltr),
                top = it.calculateTopPadding() + RecipeTheme.paddings.verticalInBetweenSmall,
                bottom = it.calculateBottomPadding() + RecipeTheme.paddings.verticalInBetweenSmall,
            ),
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.hey_user),
                    style = RecipeTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = RecipeTheme.paddings.horizontal)
                )
                Text(
                    text = stringResource(id = R.string.discover_tasty_and_healthy_receipt),
                    color = RecipeTheme.colorScheme.subText,
                    style = RecipeTheme.typography.labelLarge,
                    modifier = Modifier.padding(horizontal = RecipeTheme.paddings.horizontal)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search_any_recipe),
                            style = RecipeTheme.typography.bodyMedium,
                            color = RecipeTheme.colorScheme.subText,
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = RecipeTheme.colorScheme.textFieldBackground,
                        unfocusedContainerColor = RecipeTheme.colorScheme.textFieldBackground,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = RecipeTheme.paddings.horizontal)
                        .clickable {

                        },
                    readOnly = true
                )
                Spacer(modifier = Modifier.height(RecipeTheme.paddings.verticalInBetweenLarge))
                Text(
                    text = stringResource(id = R.string.popular_recipes),
                    style = RecipeTheme.typography.titleMedium,
                    color = RecipeTheme.colorScheme.heading,
                    modifier = Modifier.padding(horizontal = RecipeTheme.paddings.horizontal)
                )
                Spacer(modifier = Modifier.height(RecipeTheme.paddings.verticalInBetween))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(RecipeTheme.paddings.horizontal),
                    contentPadding = PaddingValues(horizontal = RecipeTheme.paddings.horizontal)
                ) {
                    items(
                        items = uiState.randomRecipe,
                        key = { it.id }
                    ) {
                        PopularRecipe(it) {
//                            navigateTo(it.recipeId)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(RecipeTheme.paddings.verticalInBetweenLarge))
                Text(
                    text = stringResource(id = R.string.all_recipes),
                    style = RecipeTheme.typography.titleMedium,
                    color = RecipeTheme.colorScheme.heading,
                    modifier = Modifier.padding(horizontal = RecipeTheme.paddings.horizontal)
                )
                Spacer(modifier = Modifier.height(RecipeTheme.paddings.vertical))
            }
            items(
                items = uiState.allRecipe,
                key = { it.id }
            ) {
                RecipeCard(it){

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