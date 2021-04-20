package com.example.convidados.ViewModel
import GuestRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.Service.Model.GuestModel

//1 - Estender ViewModel()
//2 - ir para o arquivo do guest form activity

class GuestFormViewModel(application:Application): AndroidViewModel(application){
    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    fun save(name: String, presence: Boolean) {
        val guest = GuestModel(name = name, presence=presence)
        mSaveGuest.value = mGuestRepository.save(guest)

    }

}
