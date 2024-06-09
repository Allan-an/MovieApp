package com.example.movielistingapp.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.movielistingapp.R
import com.example.movielistingapp.databinding.FragmentMovieDetailsBinding


import com.example.movielistingapp.model.Movie

class MovieDetailsFragment : Fragment() {
    private var selectedMovie: Movie? = null
    private lateinit var layoutBinding: FragmentMovieDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        selectedMovie = arguments?.getParcelable(MOVIE) as Movie?
        layoutBinding = FragmentMovieDetailsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        Glide.with(requireContext())
            .load(selectedMovie?.posterurl)
            .placeholder(R.drawable.place_holder)
            .into(layoutBinding.posterImage)
        layoutBinding.movieTitle.text = selectedMovie?.title
        layoutBinding.movieDecs.text = selectedMovie?.desc
        layoutBinding.movieGenres.text = selectedMovie?.genre?.joinToString(separator = ",")
        layoutBinding.movieReleaseDateTxt.text = selectedMovie?.release
        layoutBinding.movieRatingTxt.text = selectedMovie?.rating
        return layoutBinding.root
    }

    companion object {
        private const val MOVIE = "movie"
        fun newInstance(movie: Movie) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE,movie)
                }
            }
    }
}