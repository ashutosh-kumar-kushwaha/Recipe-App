package me.ashutoshkk.recipeapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.ashutoshkk.recipeapp.data.repository.RecipeRepositoryImpl
import me.ashutoshkk.recipeapp.domain.repository.RecipeRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRecipeRepository(repository: RecipeRepositoryImpl): RecipeRepository

}