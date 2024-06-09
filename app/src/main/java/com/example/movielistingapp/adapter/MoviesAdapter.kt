package com.example.movielistingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielistingapp.R
import com.example.movielistingapp.databinding.ItemMovieBinding
import com.example.movielistingapp.model.Movie


class MoviesAdapter (private val context: Context, private val moviesList: List<Movie>,private val onMovieClicked:(Movie)->Unit) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val itemBinding = ItemMovieBinding.inflate(LayoutInflater.from(context),parent,false)
        return MoviesViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
    val movie = moviesList[position]
    holder.movieTitleTxt.text = moviesList[position].title
    holder.movieContainer.setOnClickListener {
        onMovieClicked.invoke(movie)
    }
    Glide.with(context)
        .load(movie.posterurl)
        .placeholder(R.drawable.place_holder)
        .into(holder.moviePosterImage)
    }

    override fun getItemCount(): Int {
    return  moviesList.size
    }
    class MoviesViewHolder(itemBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val movieTitleTxt = itemBinding.movieTitle
        val moviePosterImage = itemBinding.posterImage
        val movieContainer = itemBinding.movie
    }
}