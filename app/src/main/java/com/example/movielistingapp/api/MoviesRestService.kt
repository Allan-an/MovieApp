package com.example.movielistingapp.api

import android.util.Log
import com.example.movielistingapp.api.apiInterface.MovieDetailsApiInterface
import com.example.movielistingapp.model.Genre
import com.example.movielistingapp.model.Movie

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MoviesRestService {

    private lateinit var retrofit: Retrofit
    val baseURL = "https://webeteer.com/"
    val okHttp = OkHttpClient.Builder()
        .readTimeout(SERVER_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(SERVER_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .build()

    init {
        init()
    }

    private fun init() {
        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()
    }

    fun getMoviesDetails(onMoviesFetched:(ArrayList<Genre>)->Unit) {
        val apiInterface = retrofit.create(MovieDetailsApiInterface::class.java)
        val genreList = ArrayList<Genre>()
        val myCall = apiInterface.getMoviesListDetails()
        myCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("testingapi", "onResponse: ${response.body()}")
                val jsonObject = response.body()?.string()?.let { JSONObject(it) }
                val genres = jsonObject?.getJSONArray("homeData")
                if(genres!=null && genres.length()!=0){
                    for(i in 0 until genres.length()){
                        var genre = genres.getJSONObject(i)
                        var genreId = genre.getString("id")
                        var genreMovieList = getMoviesUnderGenre(genre.getJSONArray("movieslist"))
                        var type = genre.getInt("type")
                        var genreTitle = genre.getString("genre")
                        genreList.add(Genre(id = genreId, genreTitle = genreTitle, type = type, moviesList = genreMovieList))
                        Log.d("testingapi", "onResponse: ${genre}")
                    }
                    onMoviesFetched.invoke(genreList)
                }
                Log.d("testingapi", "onResponse: $jsonObject")
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("testingapi", "onResponse: ${t.message} ${t.cause}")
            }
        })
    }

    private fun getMoviesUnderGenre(moviesListJsonArray: JSONArray): ArrayList<Movie> {
        var moviesList = ArrayList<Movie>()
        for (i in 0 until moviesListJsonArray.length()) {
            val movie = moviesListJsonArray.getJSONObject(i)
            val id = movie.getString("id")
            val title = movie.getString("title")
            val desc = movie.getString("desc")
            val genres = movie.getJSONArray("genre").let {genrelist->
                (0 until genrelist.length()).map {index->
                genrelist.getString(index)
                }
            }
            val release = movie.getString("release")
            val rating = movie.getString("rating")
            val posterurl = movie.getString("posterurl")
            moviesList.add(Movie(id = id, title = title, desc = desc, genre = genres, rating = rating, release = release, posterurl = posterurl))
        }
        return moviesList
    }

    private fun getGenresOfMovie(genresListJsonArray: JSONArray): ArrayList<String> {
        var moviesList = ArrayList<String>()
        for (i in 0 until genresListJsonArray.length()) {
            val movie = genresListJsonArray.getJSONObject(i)
            val id = movie.getString("id")
            val title = movie.getString("title")
            val desc = movie.getString("desc")
            val genres = movie.getJSONArray("genre")




        }
        return moviesList
    }

    companion object {
        val instance = MoviesRestService()
        const val SERVER_WRITE_TIMEOUT: Long = 90 //in seconds
        const val SERVER_CONNECTION_TIMEOUT: Long = 90 //in seconds
    }
}