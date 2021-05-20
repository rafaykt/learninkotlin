package com.example.filemanipulator.service.repository.remote

import androidx.room.*
import com.example.filemanipulator.service.model.Funcionario
import io.reactivex.Observable

@Dao
interface FuncionarioDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(funcionario: Funcionario)

    @Delete
    suspend fun delete(funcionario:Funcionario)

    @Query ("SELECT * FROM Funcionario")
    suspend fun getFuncionarioList() : List<Funcionario>

    @Query ("UPDATE Funcionario SET descFuncionario = :descFuncionario, complemento = :complemento, reservado1 = :reservado1 , reservado2 = :reservado2 WHERE codFuncionario = :codFuncionario ")
    suspend fun updateFuncionario (codFuncionario: Long, descFuncionario: String, complemento: String, reservado1: String, reservado2: String)
}