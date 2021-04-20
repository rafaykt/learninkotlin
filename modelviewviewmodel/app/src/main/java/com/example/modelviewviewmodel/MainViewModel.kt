package com.example.modelviewviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var mRepository = PersonRepository()
   private var mTextWelcome = MutableLiveData<String>()

    var textWelcome = mTextWelcome

    init{
        mTextWelcome.value = "Hello World"
    }

    private var mLogin = MutableLiveData<Boolean>()
    var login = mLogin

    fun login(login: String){
        val ret = mRepository.login(login)
        mLogin.value = ret
    }


}