package com.wallstreet.airlines.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wallstreet.airlines.models.Passenger

@Dao
interface PassengerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPassengers(passengers: List<Passenger>)

    @Query("Select * from passengers")
    fun getAllPassengers(): List<Passenger>
}