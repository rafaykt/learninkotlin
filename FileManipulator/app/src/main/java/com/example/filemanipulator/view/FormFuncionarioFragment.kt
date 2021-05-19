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

        binding.submitButtonForm.setOnClickListener{
            cadastrarFuncionario(binding)
        }



        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormFuncionarioViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun cadastrarFuncionario(binding: FormFuncionarioFragmentBinding){
        val funcionario = Funcionario(
            binding.formId.editText?.text.toString().toLong(),
                    binding.formNome.editText?.text.toString(),
                    binding.formComplemento.editText?.text.toString(),
                    binding.formReservado1.editText?.text.toString(),
                    binding.formReservado2.editText?.text.toString()
        )
        viewModel.saveFuncionario(funcionario)
        val directions = FormFuncionarioFragmentDirections.actionFormFuncionarioToNavigationHome()
        view?.findNavController()?.navigate(directions)

    }

}