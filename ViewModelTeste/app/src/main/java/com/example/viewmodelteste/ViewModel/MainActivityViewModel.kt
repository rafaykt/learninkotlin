package com.example.viewmodelteste.ViewModel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.CountDownLatch

class MainActivityViewModel: ViewModel() {

    private lateinit var timer: CountDownTimer

    private val _seconds = MutableLiveData<Int>()
    private val _miliseconds = MutableLiveData<Long>()
    val milisecs: LiveData<Long> get() = _miliseconds

    var finished = MutableLiveData<Boolean>()

    fun startTime(){
        timer = object: CountDownTimer(10000,1000){

            override fun onTick(millisUntilFinished: Long) {
                _miliseconds.value = millisUntilFinished
                val timeLeft = millisUntilFinished/1000
                _seconds.value = timeLeft.toInt()

            }

            override fun onFinish() {
                finished.value=true
            }

        }.start()
    }

    fun stopTimer(){
        timer.cancel()
    }

}