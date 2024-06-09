package com.example.movielistingapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.movielistingapp.R
import com.example.movielistingapp.adapter.GenresAdapter
import com.example.movielistingapp.api.MoviesRestService
import com.example.movielistingapp.databinding.ActivityMainBinding
import com.example.movielistingapp.model.Genre
import com.example.movielistingapp.ui.movie.MovieListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var adapter: GenresAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        MoviesRestService.instance.getMoviesDetails{
//            runOnUiThread {
                val genreList = it
                adapter = GenresAdapter(context = this, genresList = genreList){genre->
                navigateToMovieListScreen(genre)
                }
                viewBinding.moviesListRecyclerview.layoutManager = LinearLayoutManager(this)
                viewBinding.moviesListRecyclerview.adapter = adapter
                Log.d("testinglog", "onCreate: $genreList")
//            }
        }
    }

    private fun navigateToMovieListScreen(genre:Genre) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = MovieListFragment.newInstance(genre)
        fragmentTransaction.add(R.id.movieListFragment, fragment)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }
}