package com.jetbrains.handson.androidApp

import android.app.Dialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.FrameLayout
import android.widget.TimePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jetbrains.handson.kmm.shared.SpaceXSDK
import com.jetbrains.handson.kmm.shared.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.cancel
import java.util.*


class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "SpaceX Launches"
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }




    class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // Use the current time as the default values for the picker
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            // Create a new instance of TimePickerDialog and return it
            return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
        }

        override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
            // Do something with the time chosen by the user
//            Toast.makeText(requireContext(), "Entrada:${hourOfDay}:${minute}", Toast.LENGTH_SHORT).show()

            if(view.id == R.id.entrada1){
                println("aqui")
                Toast.makeText(requireContext(), "Entrada1", Toast.LENGTH_SHORT).show()
            }
            if(view.id == R.id.entrada2){
                Toast.makeText(requireContext(), "Entrada2", Toast.LENGTH_SHORT).show()
            }
            if(view.id == R.id.saida1){
                Toast.makeText(requireContext(), "saida1", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun showTimePickerDialog(v: View) {
//            TimePickerFragment().show(supportFragmentManager, "timePicker")
        TimePickerFragment().
    }


}