package com.example.filemanipulator.service.repository

import android.content.Context
import android.net.Uri
import com.example.filemanipulator.service.model.Funcionario
import com.example.filemanipulator.service.repository.remote.FuncionarioDataBase
import com.example.filemanipulator.service.util.FileManager


class FuncionarioRepository(context: Context) {

    private val mDatabase = FuncionarioDataBase.getDatabase(context).funcionarioDAO()
    private val file = FileManager()

    suspend fun getFuncionarioList(): List<Funcionario> {
        return mDatabase.getFuncionarioList()
    }

    suspend fun save (funcionario: Funcionario){
        return mDatabase.save(funcionario)
    }

    suspend fun delete (funcionario: Funcionario){
        return mDatabase.delete(funcionario)
    }

    suspend fun getFuncionariosFromFile( uri: Uri, context: Context): List<Funcionario>{
        var listaFuncionario = file.readFuncionariosFromFile(uri, context)
        listaFuncionario.forEach{
            save(it)
        }
        listaFuncionario = getFuncionarioList()
        return listaFuncionario
    }

    suspend fun writeFuncionariosIntoFile( context: Context){
        val lista = getFuncionarioList()
        return file.writeFuncionariosIntoFile(context, lista)
    }

    suspend fun update (funcionario: Funcionario){
        return mDatabase.updateFuncionario(funcionario.codFuncionario, funcionario.descFuncionario, funcionario.complemento, funcionario.reservado1, funcionario.reservado2)
    }



}