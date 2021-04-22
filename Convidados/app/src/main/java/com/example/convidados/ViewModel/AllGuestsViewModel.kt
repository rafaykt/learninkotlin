package com.example.convidados.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.Service.Model.GuestModel

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository.getInstance(application.applicationContext)
    val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(){
        mGuestList.value = mGuestRepository.getAll()
    }
}