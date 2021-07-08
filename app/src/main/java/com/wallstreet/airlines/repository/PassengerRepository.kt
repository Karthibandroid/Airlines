package com.wallstreet.airlines.repository

import com.wallstreet.airlines.database.dao.AirlineDao
import com.wallstreet.airlines.database.dao.PassengerDao
import com.wallstreet.airlines.models.Airline
import com.wallstreet.airlines.models.Passenger
import com.wallstreet.airlines.network.RetrofitManager
import com.wallstreet.airlines.network.api.PassengerApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PassengerRepository constructor(
    private val retrofitService: RetrofitManager,
    private val passengerDao: PassengerDao,
    private val airlineDao: AirlineDao)  {

    fun getAllPassengers(page: Int, size: Int) =
        retrofitService.createService(PassengerApi::class.java).getPassengerList(page, size)

    suspend fun insertPassengers(passengers: List<Passenger>) = withContext(Dispatchers.IO) {
            passengerDao.insertPassengers(passengers)
    }

    suspend fun insertAirlines(airlines: List<Airline>) = withContext(Dispatchers.IO) {
       airlineDao.insert(airlines)
    }

    suspend fun getAllPassengersFromDB(): List<Passenger> = withContext(Dispatchers.IO) {
         passengerDao.getAllPassengers()
    }
}