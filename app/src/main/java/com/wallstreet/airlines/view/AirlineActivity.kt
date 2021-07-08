package com.wallstreet.airlines.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.wallstreet.airlines.R
import kotlinx.android.synthetic.main.activity_main.*

class AirlineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed({
            viewSplash.visibility = View.GONE
            addFragment(FragmentPassengerList.getInstance(), true)
        }, 2000)
    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean) {
        supportFragmentManager.beginTransaction().add(R.id.frameContainer, fragment).apply {
            if(addToBackStack) {
                addToBackStack(null)
            }
            commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}