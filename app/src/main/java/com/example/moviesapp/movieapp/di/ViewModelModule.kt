package com.example.moviesapp.movieapp.di

import com.example.moviesapp.movieapp.repository.MovieRepository
import com.example.moviesapp.movieapp.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(impl: MovieRepositoryImpl): MovieRepository
}