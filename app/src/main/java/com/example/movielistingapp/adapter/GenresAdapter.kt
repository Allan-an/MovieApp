package com.example.movielistingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistingapp.databinding.ItemGenreBinding
import com.example.movielistingapp.model.Genre

class GenresAdapter(private val context: Context, private val genresList: List<Genre>,private val onMovieGenreClicked:(Genre)->Unit) : RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val itemBinding =ItemGenreBinding.inflate(LayoutInflater.from(context),parent,false)
        return GenresViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genresItem = genresList[position]
        holder.genreItemTxt.text = genresItem.genreTitle
        holder.genreItemTxt.setOnClickListener {
            onMovieGenreClicked.invoke(genresItem)
                 }
//        holder.itemView.setBackgroundColor(menuItem.backgroundColor)
    }

    override fun getItemCount(): Int {
        return genresList.size
    }

    class GenresViewHolder(itemBinding: ItemGenreBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val genreItemTxt = itemBinding.genreItemTxt
    }
}
