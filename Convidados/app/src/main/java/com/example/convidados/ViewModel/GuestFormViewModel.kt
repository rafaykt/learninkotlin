package com.example.convidados.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.Service.Model.GuestModel
import com.example.convidados.Service.Repository.GuestRepository

//1 - Estender ViewModel()
//2 - ir para o arquivo do guest form activity

class GuestFormViewModel: ViewModel(){
    private val mGuestRepository: GuestRepository = GuestRepository()

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    fun save(name: String, presence: Boolean) {
        val guest = GuestModel(name, presence)
        mGuestRepository.save(guest)

    }

}
