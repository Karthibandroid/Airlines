package com.wallstreet.airlines.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wallstreet.airlines.models.Airline
import com.wallstreet.airlines.repository.PassengerRepository
import com.wallstreet.airlines.models.Passenger
import com.wallstreet.airlines.network.models.PassengersApiResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassengerListViewModel constructor(private val passengerRepository: PassengerRepository): ViewModel() {

    var passengerList = MutableLiveData<List<Passenger>>()
    private fun getPassengers() {
        val request = passengerRepository.getPassengers(0, 20)
        request.enqueue(object : Callback<PassengersApiResponse> {
            override fun onResponse(
                call: Call<PassengersApiResponse>,
                response: Response<PassengersApiResponse>
            ) {
                val passengerResponse = response.body()
                viewModelScope.launch {
                    passengerResponse?.passengers?.let {
                        val passengers = ArrayList<Passenger>()
                        val airLines = ArrayList<Airline>()
                        for(passenger in it) {
                            passengers.add(Passenger(passenger.id, passenger.name, passenger.trips, passenger.airline.id))
                            airLines.add(passenger.airline)
                        }
                        passengerRepository.insertPassengers(passengers)
                        passengerRepository.insertAirlines(airLines)
                        passengerList.postValue(passengers)
                    }
                }
            }

            override fun onFailure(call: Call<PassengersApiResponse>, t: Throwable) {
                t.message?.let { Log.e("PassengerListViewModel", it) }
            }

        })
    }

    fun getAllPassengersFromDB() {
        viewModelScope.launch {
            val passengers = passengerRepository.getAllPassengersFromDB()
            if(passengers.isEmpty()) {
                getPassengers()
            } else {
                passengerList.postValue(passengerRepository.getAllPassengersFromDB())
            }
        }
    }
}