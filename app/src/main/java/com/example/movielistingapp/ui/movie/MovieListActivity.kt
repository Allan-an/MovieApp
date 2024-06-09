package com.example.movielistingapp.ui.movie

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.movielistingapp.R
import com.example.movielistingapp.databinding.ActivityMainBinding

class MovieListActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
//    private lateinit var adapter: MoviesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_movie_list1)
    }
}
