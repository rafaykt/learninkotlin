package com.example.filemanipulator.service.repository

import android.content.Context
import com.example.filemanipulator.service.model.Funcionario
import com.example.filemanipulator.service.repository.remote.FuncionarioDataBase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class FuncionarioRepository(context: Context) {

    private val mDatabase = FuncionarioDataBase.getDatabase(context).funcionarioDAO()

    fun getFuncionarioList(): Observable<List<Funcionario>> {
        return mDatabase.getFuncionarioList()
    }

    fun save (funcionario: Funcionario): Completable{
        return Completable.create{
            try{
                mDatabase.save(funcionario)
                it.onComplete()
            }catch (e: Exception){
                it.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    fun delete (funcionario: Funcionario): Completable{
        return Completable.create{
            try{
                mDatabase.delete(funcionario)
                it.onComplete()
            }catch (e: Exception){
                it.onError(e)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }
}