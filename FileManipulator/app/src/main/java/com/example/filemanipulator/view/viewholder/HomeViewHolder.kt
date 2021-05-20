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
        binding.idFuncionario.text = funcionario.codFuncionario.toString()
        binding.nomeFuncionario.text = funcionario.descFuncionario
        binding.cargoFuncionario.text = funcionario.complemento
        binding.reservado1Funcionario.text = funcionario.reservado1
        binding.reservado2Funcionario.text = funcionario.reservado2



        //bind eventos
        binding.body.setOnClickListener{
            listener.showDetails(funcionario)
        }
        binding.buttonDelete.setOnClickListener{
            listener.deleteFunc(funcionario)
        }

    }

}