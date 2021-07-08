package com.wallstreet.airlines.database

import android.content.Context
import androidx.room.*
import com.wallstreet.airlines.database.dao.AirlineDao
import com.wallstreet.airlines.database.dao.PassengerDao
import com.wallstreet.airlines.models.Airline
import com.wallstreet.airlines.models.Passenger

@Database(entities = [Passenger::class, Airline::class], version = 1)
abstract class AirlineDatabase: RoomDatabase() {
    abstract fun passengerDao(): PassengerDao
    abstract fun airlineDao(): AirlineDao

    companion object {

        @Volatile
        private var INSTANCE: AirlineDatabase? = null

        fun getDatabase(context: Context): AirlineDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AirlineDatabase::class.java, "airline_database"
                        ).build()
                }
            }
            return INSTANCE
        }
    }
}