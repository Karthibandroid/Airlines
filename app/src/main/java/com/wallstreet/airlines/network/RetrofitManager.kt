package com.wallstreet.airlines.network

import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.instantwebtools.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}