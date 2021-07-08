package com.wallstreet.airlines.network.api

import com.wallstreet.airlines.network.models.PassengersApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PassengerApi {
    @GET("/v1/passenger")
    fun getPassengerList(@Query("page") page: Int, @Query("size") size: Int) : Call<PassengersApiResponse>
}