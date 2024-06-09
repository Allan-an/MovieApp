package com.example.movielistingapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movielistingapp.api.MoviesRestService
import com.example.movielistingapp.model.Genre

class MovieViewModel:ViewModel() {
    private val _moviesDatas = MutableLiveData<List<Genre>>()
    val moviesDatas:LiveData<List<Genre>> = _moviesDatas
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>  = _loading

    fun getMoviesDetails(){
        _loading.value = true
        MoviesRestService.instance.getMoviesDetails{genreList->
            _moviesDatas.value = genreList
            _loading.value = false
        }
    }
}