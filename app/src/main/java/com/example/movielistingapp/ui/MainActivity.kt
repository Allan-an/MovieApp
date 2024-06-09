package com.example.movielistingapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielistingapp.R
import com.example.movielistingapp.adapter.GenresAdapter
import com.example.movielistingapp.databinding.ActivityMainBinding
import com.example.movielistingapp.model.Genre
import com.example.movielistingapp.model.Movie
import com.example.movielistingapp.ui.movie.MovieDetailsFragment
import com.example.movielistingapp.ui.movie.MovieListFragment
import com.example.movielistingapp.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var adapter: GenresAdapter
    private val moviesViewModel:MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        moviesViewModel.getMoviesDetails()
        moviesViewModel.moviesDatas.observe(this, Observer {genreList->
            adapter = GenresAdapter(context = this, genresList = genreList){genre->
                navigateToMovieListScreen(genre)
            }
            viewBinding.moviesListRecyclerview.layoutManager = LinearLayoutManager(this)
            viewBinding.moviesListRecyclerview.adapter = adapter
        })
        moviesViewModel.loading.observe(this, Observer {isLoading->
            viewBinding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
        })
    }

    fun navigateToMovieListScreen(genre:Genre) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = MovieListFragment.newInstance(genre)
        fragmentTransaction.add(R.id.movieListFragment, fragment)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }

    fun navigateToMovieDetailsScreen(movie: Movie) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = MovieDetailsFragment.newInstance(movie)
        fragmentTransaction.add(R.id.movieListFragment, fragment)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }
}