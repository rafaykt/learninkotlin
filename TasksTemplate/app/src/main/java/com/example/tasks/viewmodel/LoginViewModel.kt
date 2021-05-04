package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.repository.PersonRepository
import com.example.tasks.service.repository.PriorityRepository
import com.example.tasks.service.repository.local.SecurityPreferences
import com.example.tasks.service.repository.remote.RetrofitClient
import retrofit2.Retrofit
import retrofit2.http.Header

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val mPersonRepository = PersonRepository(application)
    private val mSharedPreferences = SecurityPreferences(application)
    private val mPriorityRepository = PriorityRepository(application)

    private val mLogin = MutableLiveData<ValidationListener>()
    var login : LiveData<ValidationListener> = mLogin

    private val mLoggeduser = MutableLiveData<Boolean>()
    var loggedUser : LiveData<Boolean> = mLoggeduser


    /**
     * Faz login usando API
     */
    fun doLogin(email: String, password: String) {
        mPersonRepository.login(email,password, object : APIListener<HeaderModel>{
            override fun onSuccess(model: HeaderModel) {
                mSharedPreferences.store(TaskConstants.SHARED.TOKEN_KEY, model.token)
                mSharedPreferences.store(TaskConstants.SHARED.PERSON_KEY, model.personKey)
                mSharedPreferences.store(TaskConstants.SHARED.PERSON_NAME, model.name)
                mLogin.value = ValidationListener()

                RetrofitClient.addHeader(model.token, model.personKey)
            }

            override fun onFailure(str: String) {
                mLogin.value = ValidationListener(str)
            }

        })
    }

    /**
     * Verifica se usuário está logado
     */
    fun verifyLoggedUser() {
        val token = mSharedPreferences.get(TaskConstants.SHARED.TOKEN_KEY)
        val personKey = mSharedPreferences.get(TaskConstants.SHARED.PERSON_KEY)

        RetrofitClient.addHeader(token, personKey)
        val logged = (token!= "" && personKey != "")

        if (!logged){
            mPriorityRepository.all()
        }
        mLoggeduser.value = logged
    }

}