package com.example.movielistingapp.api.apiInterface


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieDetailsApiInterface {
    @Headers("Content-Type: application/json")
    @GET("dummy.json")
    fun getMoviesListDetails(): Call<ResponseBody>
}