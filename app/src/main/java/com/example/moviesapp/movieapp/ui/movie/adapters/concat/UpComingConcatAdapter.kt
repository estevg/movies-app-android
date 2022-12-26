package com.example.moviesapp.movieapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.UpcomingMoviewRowBinding
import com.example.moviesapp.movieapp.core.BaseConcatHolder
import com.example.moviesapp.movieapp.ui.movie.adapters.MovieAdapter

class UpComingConcatAdapter(private val moviesAdapter: MovieAdapter, private val title: String = "UpComing") :
    RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            UpcomingMoviewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder) {
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1


    private inner class ConcatViewHolder(val binding: UpcomingMoviewRowBinding) :
        BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvUpcomingMovies.adapter = adapter
            binding.title.text = title

        }

    }

}