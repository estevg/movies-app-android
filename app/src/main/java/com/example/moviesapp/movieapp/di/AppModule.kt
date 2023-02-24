package com.example.moviesapp.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.moviesapp.movieapp.application.AppConstants
import com.example.moviesapp.movieapp.data.local.AppDatabase
import com.example.moviesapp.movieapp.repository.WebService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "movie_table"
    ).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideCocktailDao(db: AppDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

}