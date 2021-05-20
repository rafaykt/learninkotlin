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

    fun saveFuncionario(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.save(funcionario)
            gravarFuncionario()
        }
    }

    fun updateFuncionario(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.update(funcionario)
            gravarFuncionario()
        }
    }


    fun gravarFuncionario() {
        viewModelScope.launch(Dispatchers.IO) { mRepository.writeFuncionariosIntoFile(getApplication()) }
    }

    fun validarDados(funcionario: Funcionario): Boolean {
        return !((funcionario.descFuncionario == "") ||
                (funcionario.codFuncionario == "") ||
                (funcionario.complemento == "") ||
                (funcionario.reservado1 == "") ||
                (funcionario.reservado2 == ""))
    }
}