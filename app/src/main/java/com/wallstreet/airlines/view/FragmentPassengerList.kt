package com.wallstreet.airlines.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wallstreet.airlines.R
import com.wallstreet.airlines.adapter.PassengerAdapter
import com.wallstreet.airlines.database.AirlineDatabase
import com.wallstreet.airlines.models.Passenger
import com.wallstreet.airlines.network.RetrofitManager
import com.wallstreet.airlines.repository.PassengerRepository
import com.wallstreet.airlines.viewmodels.PassengerListViewModel
import com.wallstreet.airlines.viewmodels.viewmodelsfactory.PassengerListViewModelFactory

class FragmentPassengerList:  Fragment(), PassengerAdapter.OnPassengerClickListener {

    companion object {
        fun getInstance(): FragmentPassengerList {
            return FragmentPassengerList()
        }
    }
    private lateinit var airlinesViewModel: PassengerListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var passengerAdapter: PassengerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        airlinesViewModel = ViewModelProvider(
            this, PassengerListViewModelFactory(PassengerRepository(RetrofitManager(), AirlineDatabase.getDatabase(requireActivity())
                ?.passengerDao()!!, AirlineDatabase.getDatabase(requireActivity())
                ?.airlineDao()!!))
        ).get(PassengerListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_passanger_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewPassenger)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
       airlinesViewModel.passengerList.observe(viewLifecycleOwner, Observer {
           passengerAdapter = PassengerAdapter(it as ArrayList<Passenger>, this)
           recyclerView.adapter = passengerAdapter
        })
        airlinesViewModel.getAllPassengersFromDB()
    }

    override fun onPassengerClick(passenger: Passenger) {
        (activity as AirlineActivity).addFragment(FragmentPassengerDetail.getInstance(passenger.name, passenger.airlineId), true)
    }
}