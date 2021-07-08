package com.wallstreet.airlines.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wallstreet.airlines.R
import com.wallstreet.airlines.models.Passenger

class PassengerAdapter(var passengerList: ArrayList<Passenger>, var onPassengerClickListener: OnPassengerClickListener): RecyclerView.Adapter<PassengerAdapter.PassengerHolder>() {


    interface OnPassengerClickListener {
        fun onPassengerClick(passenger: Passenger)
    }
    /*private var passengerList: ArrayList<Passenger> = ArrayList()

    init {
        this.passengerList = passengerList
    }*/

    fun addPassengerList(passengerList: ArrayList<Passenger>) {
        passengerList.addAll(passengerList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerHolder {
        Log.v("PassengerAdapter", "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_passanger, parent, false)
        return PassengerHolder(view)
    }

    override fun onBindViewHolder(holder: PassengerHolder, position: Int) {
        holder.bindItems(passengerList[position])
        holder.itemView.setOnClickListener{
            onPassengerClickListener.onPassengerClick(passengerList[position])
        }
    }

    override fun getItemCount(): Int {
        Log.v("PassengerAdapter", "getItemCount")
        return passengerList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //the class is hodling the list view
    class PassengerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(passenger: Passenger) {
            val textViewName = itemView.findViewById(R.id.txtPassengerName) as TextView
            val textViewAddress  = itemView.findViewById(R.id.txtTrips) as TextView
            textViewName.text = passenger.name
            textViewAddress.text = "" + passenger.trips
        }
    }

}