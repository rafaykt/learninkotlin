package com.example.viewmodelteste

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelteste.ViewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.startTime()
        viewModel.milisecs.observe(this, Observer {
            textViewContador.text = it.toString()
        })
        viewModel.finished.observe(this, Observer {
            if (it){
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }
        })


    }



}