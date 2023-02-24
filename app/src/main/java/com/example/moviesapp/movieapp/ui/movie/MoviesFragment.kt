package com.example.moviesapp.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesBinding
import com.example.moviesapp.movieapp.core.Resource
import com.example.moviesapp.movieapp.data.model.Movie
import com.example.moviesapp.movieapp.presentation.MovieViewModel
import com.example.moviesapp.movieapp.ui.movie.adapters.MovieAdapter
import com.example.moviesapp.movieapp.ui.movie.adapters.concat.UpComingConcatAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var concatAdapter: ConcatAdapter


    private val viewModel by viewModels<MovieViewModel>()

   /* private val viewModel by viewModels<MovieViewModel> {
        MovieViewModalFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetroFitClient.webservice),
                LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)
        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("Respuesta", "Respuesta ${result.data}")

                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpComingConcatAdapter(
                                MovieAdapter(
                                    result.data.t1.results,
                                    this@MoviesFragment
                                ), "Upcoming Movies"
                            )
                        )
                        addAdapter(
                            1,
                            UpComingConcatAdapter(
                                MovieAdapter(
                                    result.data.t2.results,
                                    this@MoviesFragment
                                ), "Top Rate Movies"
                            )
                        )
                        addAdapter(
                            2,
                            UpComingConcatAdapter(
                                MovieAdapter(
                                    result.data.t3.results,
                                    this@MoviesFragment
                                ), "Popular Movies"
                            )
                        )
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    Log.d("Error", "Hubo un error ${result.exception}")

                    binding.progressBar.visibility = View.GONE
                }
            }

        })

    }

    override fun onMovieClick(movie: Movie) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )

        findNavController().navigate(action)
    }

}