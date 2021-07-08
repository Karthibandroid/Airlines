package com.wallstreet.airlines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wallstreet.airlines.R
import com.wallstreet.airlines.database.AirlineDatabase
import com.wallstreet.airlines.repository.AirlineRepository
import com.wallstreet.airlines.repository.PassengerRepository
import com.wallstreet.airlines.viewmodels.PassengerDetailViewModel
import com.wallstreet.airlines.viewmodels.viewmodelsfactory.PassengerDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_passenger_detail.*

class FragmentPassengerDetail: Fragment() {

    companion object{
        const val BUNDLE_PASSENGER_NAME = "passengerName"
        const val BUNDLE_AIRLINE_ID = "airlineId"
        fun getInstance(passengerName: String, airlineId: Int): FragmentPassengerDetail {
            val fragmentPassengerDetail = FragmentPassengerDetail()
            val bundle = Bundle()
            bundle.putString(BUNDLE_PASSENGER_NAME, passengerName)
            bundle.putInt(BUNDLE_AIRLINE_ID, airlineId)
            fragmentPassengerDetail.arguments = bundle
            return fragmentPassengerDetail
        }
    }

    private lateinit var passengerDetailViewModel: PassengerDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passengerDetailViewModel = ViewModelProvider(
            this, PassengerDetailViewModelFactory(AirlineRepository( AirlineDatabase.getDatabase(requireActivity())?.airlineDao()!!))
        ).get(PassengerDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_passenger_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtPassengerName.text = arguments?.getString(BUNDLE_PASSENGER_NAME)
        passengerDetailViewModel.airline.observe(viewLifecycleOwner, Observer {
            txtAirlineName.text = it.name
            txtSlogan.text = it.slogan
            txtHeadQuaters.text = it.headQuaters
            txtWebsite.text = it.website
            txtEstablished.text = it.established
        })
        passengerDetailViewModel.getAirlineFromDB(arguments?.getInt(BUNDLE_AIRLINE_ID)!!)
    }
}