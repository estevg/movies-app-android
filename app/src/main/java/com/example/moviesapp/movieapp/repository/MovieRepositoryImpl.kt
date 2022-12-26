package com.example.moviesapp.movieapp.repository

import com.example.moviesapp.movieapp.data.model.MovieList
import com.example.moviesapp.movieapp.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRateMovies(): MovieList = dataSource.getTopRateMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()
}