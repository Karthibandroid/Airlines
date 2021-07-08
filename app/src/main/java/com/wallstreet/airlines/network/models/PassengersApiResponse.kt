package com.wallstreet.airlines.network.models

import com.google.gson.annotations.SerializedName
import com.wallstreet.airlines.models.Passenger

data class PassengersApiResponse(
    val totalPassengers: Int,
    val totalPages: Int,
    @SerializedName("data") val passengers: List<PassengerResponse>
)
