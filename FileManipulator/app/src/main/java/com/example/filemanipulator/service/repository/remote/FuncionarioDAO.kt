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


}