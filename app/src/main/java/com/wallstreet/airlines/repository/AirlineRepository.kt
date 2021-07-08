package com.wallstreet.airlines.repository

import com.wallstreet.airlines.database.dao.AirlineDao
import com.wallstreet.airlines.database.dao.PassengerDao
import com.wallstreet.airlines.models.Airline
import com.wallstreet.airlines.network.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AirlineRepository constructor(private val airlineDao: AirlineDao)  {
    suspend fun getAirline(id: Int): Airline = withContext(Dispatchers.IO) {
        airlineDao.getAirline(id)
    }
}