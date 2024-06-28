package me.ashutoshkk.recipeapp.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.ashutoshkk.recipeapp.R

@Composable
fun HomeScreen(navigateTo: (String) -> Unit) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold {
        LazyColumn(
            contentPadding = it
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.hey_user),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            items(uiState.allRecipe){
                Text(text = it.title)
            }
        }
    }


}