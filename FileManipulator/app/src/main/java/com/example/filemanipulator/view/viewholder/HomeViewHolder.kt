package com.example.filemanipulator.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.filemanipulator.databinding.RowFuncionarioBinding
import com.example.filemanipulator.service.listener.ActionListener
import com.example.filemanipulator.service.model.Funcionario

class HomeViewHolder(
    private val binding: RowFuncionarioBinding,
    val listener: ActionListener,
    var funcionario: Funcionario? = null
) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(funcionario: Funcionario) {
        binding.idFuncionario.text = "Cod: ${funcionario.codFuncionario}"
        binding.nomeFuncionario.text = funcionario.descFuncionario.toUpperCase()
        binding.cargoFuncionario.text = "Cargo: ${funcionario.complemento}"
        binding.reservado1Funcionario.text = "R1: ${funcionario.reservado1}"
        binding.reservado2Funcionario.text = "R2: ${funcionario.reservado2}"


        //bind eventos
        binding.body.setOnClickListener{
            listener.showDetails(funcionario)
        }
        binding.buttonDelete.setOnClickListener{
            listener.deleteFunc(funcionario)
        }
    }
}