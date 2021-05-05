package com.example.desafio_filme20.service.listeners

import com.example.desafio_filme20.service.model.Film


interface MovieListener {

    /**
     * Click para edição
     */
    fun onListClick(filme: Film)

//    fun onDeleteClick(id: Int)
//
//    /**
//     * Completa tarefa
//     */
//    fun onCompleteClick(id: Int)
//
//    /**
//     * Descompleta tarefa
//     */
//    fun onUndoClick(id: Int)

}