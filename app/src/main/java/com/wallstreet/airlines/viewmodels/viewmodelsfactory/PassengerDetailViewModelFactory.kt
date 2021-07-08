package com.wallstreet.airlines.viewmodels.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wallstreet.airlines.repository.AirlineRepository
import com.wallstreet.airlines.repository.PassengerRepository
import com.wallstreet.airlines.viewmodels.PassengerDetailViewModel
import com.wallstreet.airlines.viewmodels.PassengerListViewModel

class PassengerDetailViewModelFactory constructor(private val airlineRepository: AirlineRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PassengerDetailViewModel::class.java)) {
            PassengerDetailViewModel(this.airlineRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}