package com.wallstreet.airlines.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wallstreet.airlines.models.Airline

@Dao
interface AirlineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(airlines: List<Airline>)

    @Query("Select * from Airlines where id = :id")
    fun getAirline(id: Int) : Airline
}