package com.example.moviesapp.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.movieapp.core.Resource
import com.example.moviesapp.movieapp.data.model.Movie
import com.example.moviesapp.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repo: MovieRepository): ViewModel() {

    fun fetchMainScreenMovies() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(NTuple3(repo.getUpcomingMovies(), repo.getPopularMovies(), repo.getTopRateMovies())))
        } catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }


}

/*class MovieViewModalFactory constructor(private val repo: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}*/
// Si queremos hacer multiples llamadas
data class NTuple3<T1, T2, T3>(val t1: T1, val t2: T2, val t3: T3)