package com.mytoshiba.newsapp.retrofit

import com.mytoshiba.newsapp.data.Article
import com.mytoshiba.newsapp.data.data
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("v2/top-headlines?country=us&apiKey=293a70012ce944feb7707f92d43c76f5")
    fun getnewsList(): Call<data>
}