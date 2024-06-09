package com.example.movielistingapp.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistingapp.databinding.ItemMovieBinding
import com.example.movielistingapp.model.Movie


class MoviesAdapter (private val context: Context, private val moviesList: List<Movie>,private val onMovieClicked:(Movie)->Unit) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {

    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
    return  moviesList.size
    }
    class MoviesViewHolder(itemBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {
//        val movieItemTxt = itemBinding.movieItemTxt
    }
}