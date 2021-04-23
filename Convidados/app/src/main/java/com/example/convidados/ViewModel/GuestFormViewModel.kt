package com.example.convidados.ViewModel
import GuestRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.Service.Model.GuestModel

//1 - Estender ViewModel()
//2 - ir para o arquivo do guest form activity

class GuestFormViewModel(application:Application): AndroidViewModel(application){
    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest


    private var mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest


    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel().apply {
            this.id = id
            this.name = name
            this.presence = presence
        }
        if(id==0){
            mSaveGuest.value = mGuestRepository.save(guest)
        }else{
            mSaveGuest.value = mGuestRepository.update(guest)
        }

//        mGuestRepository.save(guest)
    }

    fun load(id: Int){
        mGuest.value = mGuestRepository.get(id)
    }
}
