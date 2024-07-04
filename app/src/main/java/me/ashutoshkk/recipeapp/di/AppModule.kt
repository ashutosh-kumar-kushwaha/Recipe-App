package me.ashutoshkk.recipeapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.ashutoshkk.recipeapp.common.Constants.BASE_URL
import me.ashutoshkk.recipeapp.data.remote.SpoonacularApiService
import me.ashutoshkk.recipeapp.data.room.RecipeDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesSpoonacularApiService() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SpoonacularApiService::class.java)

    @Provides
    @Singleton
    fun providesRecipeDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RecipeDatabase::class.java,
        "recipe_database"
    ).build()

    @Provides
    @Singleton
    fun providesFavoriteRecipeDao(db: RecipeDatabase) = db.favoriteRecipeDao

}