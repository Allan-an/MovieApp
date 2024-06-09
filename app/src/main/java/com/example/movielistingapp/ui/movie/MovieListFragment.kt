package com.example.movielistingapp.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielistingapp.R
import com.example.movielistingapp.adapter.MoviesAdapter
import com.example.movielistingapp.databinding.FragmentMovieListBinding
import com.example.movielistingapp.model.Genre

class MovieListFragment : Fragment() {
    private var selectedGenre: Genre? = null
    private lateinit var layoutBinding:FragmentMovieListBinding
    private lateinit var adapter: MoviesAdapter

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        selectedGenre = arguments?.getParcelable(GENRE) as Genre?
        // Inflate the layout for this fragment
       layoutBinding = FragmentMovieListBinding.inflate(inflater,container,false)
       val moviesList = selectedGenre?.moviesList
       if(moviesList!=null)
       adapter = MoviesAdapter(context = requireContext(),moviesList = moviesList){movie->

       }
       layoutBinding.moviesListRecyclerview.layoutManager = GridLayoutManager(requireContext(),2)
       layoutBinding.moviesListRecyclerview.adapter = adapter
       return layoutBinding.root
    }

    companion object {
        private const val GENRE = "genre"
        fun newInstance(genre: Genre) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GENRE,genre)
                }
            }
    }
}