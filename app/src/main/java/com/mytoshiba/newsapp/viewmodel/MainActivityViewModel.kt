package com.mytoshiba.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mytoshiba.newsapp.data.Article
import com.mytoshiba.newsapp.data.data
import com.mytoshiba.newsapp.retrofit.RetroInstance
import com.mytoshiba.newsapp.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    lateinit var livedataList: MutableLiveData<List<Article>>

    init {
        livedataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Article>>{
        return livedataList
    }
    fun makeAPIcall(){
       val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getnewsList()
        call.enqueue(object : Callback<data>{
            override fun onFailure(call: Call<data>, t: Throwable) {
                livedataList.postValue(null)
            }
            override fun onResponse(call: Call<data>, response: Response<data>) {
                livedataList.postValue(response.body()?.articles)

            }
        })

    }
}