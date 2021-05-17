package com.example.filemanipulator.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Funcionario(
    @PrimaryKey
    var codFuncionario: Long = 0,
    var descFuncionario: String = "",
    var complemento: String = "",
    var reservado1: String = "",
    var reservado2: String = ""
)