package com.wallstreet.airlines.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Passengers")
data class Passenger(@PrimaryKey @SerializedName("_id")val id: String, val name: String, val trips: Int, val airlineId: Int)