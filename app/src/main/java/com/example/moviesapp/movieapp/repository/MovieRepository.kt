package com.example.moviesapp.movieapp.repository

import com.example.moviesapp.movieapp.data.model.MovieList

interface MovieRepository {
    suspend fun  getUpcomingMovies(): MovieList
    suspend fun getTopRateMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}