package com.example.filemanipulator.service.repository

import android.content.Context
import android.net.Uri
import com.example.filemanipulator.service.model.Funcionario
import com.example.filemanipulator.service.repository.remote.FuncionarioDataBase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.BufferedReader
import java.io.InputStreamReader

class FuncionarioRepository(context: Context) {

    private val mDatabase = FuncionarioDataBase.getDatabase(context).funcionarioDAO()

    suspend fun getFuncionarioList(): List<Funcionario> {
        return mDatabase.getFuncionarioList()
    }

    suspend fun save (funcionario: Funcionario){
        return mDatabase.save(funcionario)
    }

    suspend fun delete (funcionario: Funcionario){
        return mDatabase.delete(funcionario)
    }


}