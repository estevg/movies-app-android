package com.example.moviesapp.movieapp.data.remote

import com.example.moviesapp.movieapp.application.AppConstants
import com.example.moviesapp.movieapp.data.model.MovieList
import com.example.moviesapp.movieapp.repository.WebService

class RemoteMovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRateMovies(): MovieList = webService.getTopRateMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)
}