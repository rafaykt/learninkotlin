package com.example.filemanipulator.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.filemanipulator.R
import com.example.filemanipulator.databinding.FormFuncionarioFragmentBinding
import com.example.filemanipulator.databinding.FragmentHomeBinding
import com.example.filemanipulator.service.model.Funcionario
import com.example.filemanipulator.viewmodel.FormFuncionarioViewModel

class FormFuncionarioFragment : Fragment() {

    companion object {
        fun newInstance() = FormFuncionarioFragment()
    }

    private lateinit var viewModel: FormFuncionarioViewModel

    private var _binding: FormFuncionarioFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FormFuncionarioFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(FormFuncionarioViewModel::class.java)

        val bundle = arguments

        val funcionarioByArgument = bundle?.getParcelable<Funcionario>("funcionario")
        if (funcionarioByArgument!=null) {
            loadTelaUpdate(funcionarioByArgument, binding)
            binding.submitButtonForm.setOnClickListener {
                atualizarFuncionario(funcionarioByArgument, binding)
                val directions = FormFuncionarioFragmentDirections.actionFormFuncionarioToNavigationHome()
                view?.findNavController()?.navigate(directions)
            }

        } else {
            binding.submitButtonForm.setOnClickListener {
                cadastrarFuncionario(binding)
                val directions = FormFuncionarioFragmentDirections.actionFormFuncionarioToNavigationHome()
                view?.findNavController()?.navigate(directions)
            }
        }

        return root
    }

    private fun cadastrarFuncionario(binding: FormFuncionarioFragmentBinding) {
        val funcionario = Funcionario(
            binding.formId.editText?.text.toString().toLong(),
            binding.formNome.editText?.text.toString(),
            binding.formComplemento.editText?.text.toString(),
            binding.formReservado1.editText?.text.toString(),
            binding.formReservado2.editText?.text.toString()
        )
        viewModel.saveFuncionario(funcionario)

    }


    private fun loadTelaUpdate(funcionario: Funcionario, binding: FormFuncionarioFragmentBinding){
        binding.header.text = "Update cadastro"
        binding.formId.hint = "Código: ${funcionario.codFuncionario}"
        binding.formId.isEnabled=false

        binding.formNome.placeholderText = funcionario.descFuncionario
        binding.formComplemento.placeholderText = funcionario.complemento
        binding.formReservado1.placeholderText = funcionario.reservado1
        binding.formReservado2.placeholderText = funcionario.reservado2
        binding.submitButtonForm.text ="Atualizar usuário"
    }

    private fun atualizarFuncionario(funcionario: Funcionario, binding: FormFuncionarioFragmentBinding){
        val funcionarioUpdate: Funcionario = funcionario

        funcionarioUpdate.codFuncionario = binding.formId.editText?.text.toString().toLong()
        if(binding.formNome.editText?.text.toString()!="") funcionarioUpdate.descFuncionario = binding.formNome.editText?.text.toString()
        if(binding.formComplemento.editText?.text.toString()!="") funcionarioUpdate.complemento = binding.formComplemento.editText?.text.toString()
        if(binding.formReservado1.editText?.text.toString()!="") funcionarioUpdate.reservado1 = binding.formReservado1.editText?.text.toString()
        if(binding.formReservado2.editText?.text.toString()!="") funcionarioUpdate.reservado2 = binding.formReservado2.editText?.text.toString()

        viewModel.updateFuncionario(funcionarioUpdate)
    }

}