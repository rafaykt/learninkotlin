package com.example.filemanipulator.service.listener

import com.example.filemanipulator.service.model.Funcionario

interface ActionListener {

    fun showDetails(funcionario: Funcionario)

    fun deleteFunc( funcionario: Funcionario)
}