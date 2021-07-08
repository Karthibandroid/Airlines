package com.wallstreet.airlines.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Airlines")
data class Airline(
    @PrimaryKey
    val id: Int,
    val name: String,
    val country: String,
    val logo: String,
    val slogan: String,
    @SerializedName("head_quaters")
    val headQuaters: String,
    val website: String,
    val established: String
)
