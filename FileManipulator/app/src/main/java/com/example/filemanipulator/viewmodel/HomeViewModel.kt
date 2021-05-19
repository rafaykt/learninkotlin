package com.example.filemanipulator.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.filemanipulator.service.model.Funcionario
import com.example.filemanipulator.service.repository.FuncionarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository = FuncionarioRepository(application)

    private val mFuncionarioList = MutableLiveData<List<Funcionario>>()
    val funcionarioList: LiveData<List<Funcionario>> get() = mFuncionarioList

    fun readFile(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO){
            mFuncionarioList.postValue(mRepository.getFuncionariosFromFile(uri,getApplication()))
        }
    }

    fun getFuncionarioList(){
        viewModelScope.launch(Dispatchers.IO) {
            mFuncionarioList.postValue(mRepository.getFuncionarioList())
        }
    }

    fun deleteFuncionario(funcionario: Funcionario){
        viewModelScope.launch(Dispatchers.IO){mRepository.delete(funcionario)}
        getFuncionarioList()
    }
}