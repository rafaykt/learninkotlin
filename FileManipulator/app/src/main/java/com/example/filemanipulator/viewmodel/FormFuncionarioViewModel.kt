package com.example.filemanipulator.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filemanipulator.service.model.Funcionario

import com.example.filemanipulator.service.repository.FuncionarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FormFuncionarioViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = FuncionarioRepository(application)

    fun saveFuncionario (funcionario: Funcionario){
        viewModelScope.launch(Dispatchers.IO) { mRepository.save(funcionario) }
    }
}