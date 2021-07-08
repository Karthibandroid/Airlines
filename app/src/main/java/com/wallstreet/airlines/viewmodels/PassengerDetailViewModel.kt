package com.wallstreet.airlines.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wallstreet.airlines.models.Airline
import com.wallstreet.airlines.repository.AirlineRepository
import com.wallstreet.airlines.repository.PassengerRepository
import kotlinx.coroutines.launch

class PassengerDetailViewModel constructor(private val airlineRepository: AirlineRepository) : ViewModel() {

    var airline = MutableLiveData<Airline>()
    fun getAirlineFromDB(id: Int) {
        viewModelScope.launch {
            airline.postValue(airlineRepository.getAirline(id))
        }
    }
}