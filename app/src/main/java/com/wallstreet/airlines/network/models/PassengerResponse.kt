package com.wallstreet.airlines.network.models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wallstreet.airlines.models.Airline

data class PassengerResponse(@SerializedName("_id")val id: String, val name: String, val trips: Int, val airline: Airline)
