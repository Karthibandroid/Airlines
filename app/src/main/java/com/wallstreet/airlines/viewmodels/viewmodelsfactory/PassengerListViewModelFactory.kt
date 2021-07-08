package com.wallstreet.airlines.viewmodels.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wallstreet.airlines.repository.PassengerRepository
import com.wallstreet.airlines.viewmodels.PassengerListViewModel

class PassengerListViewModelFactory constructor(private val passengerRepository: PassengerRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PassengerListViewModel::class.java)) {
            PassengerListViewModel(this.passengerRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}