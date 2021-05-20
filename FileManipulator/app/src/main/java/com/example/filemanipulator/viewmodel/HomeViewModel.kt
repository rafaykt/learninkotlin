package com.example.filemanipulator.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.*
import com.example.filemanipulator.service.model.Funcionario
import com.example.filemanipulator.service.repository.FuncionarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository = FuncionarioRepository(application)

    private val mFuncionarioList = MutableLiveData<List<Funcionario>>()
    val funcionarioList: LiveData<List<Funcionario>> get() = mFuncionarioList

    fun readFile(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO){
            mFuncionarioList.postValue(mRepository.getFuncionariosFromFile(uri,getApplication()))
            mRepository.writeFuncionariosIntoFile(getApplication())
        }
    }

    fun getFuncionarioList(){
        viewModelScope.launch(Dispatchers.IO) {
            mFuncionarioList.postValue(mRepository.getFuncionarioList())
        }
    }

    fun deleteFuncionario(funcionario: Funcionario){
        viewModelScope.launch(Dispatchers.IO){
            mRepository.delete(funcionario)
            getFuncionarioList()
            mRepository.writeFuncionariosIntoFile(getApplication())
        }
    }


}