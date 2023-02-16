package com.example.moviesapp.movieapp.repository

import android.util.Log
import com.example.moviesapp.movieapp.core.InternetCheck
import com.example.moviesapp.movieapp.data.local.LocalMovieDataSource
import com.example.moviesapp.movieapp.data.model.MovieList
import com.example.moviesapp.movieapp.data.model.toMovieEntity
import com.example.moviesapp.movieapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSource: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }

    }

    override suspend fun getTopRateMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getTopRateMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRateMovies()
        } else {
            dataSourceLocal.getTopRateMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }
    }
}