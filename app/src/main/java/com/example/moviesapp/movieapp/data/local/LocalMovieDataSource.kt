package com.example.moviesapp.movieapp.data.local

import android.util.Log
import com.example.moviesapp.movieapp.data.model.MovieEntity
import com.example.moviesapp.movieapp.data.model.MovieList
import com.example.moviesapp.movieapp.data.model.toMovieList
import javax.inject.Inject

class LocalMovieDataSource @Inject constructor(private  val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList = movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()

    suspend fun getTopRateMovies(): MovieList = movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()

    suspend fun getPopularMovies(): MovieList = movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()

    suspend fun saveMovie(movie: MovieEntity){
        Log.d("Hola Mundo", "$movie")
        movieDao.saveMovie(movie)
    }
}