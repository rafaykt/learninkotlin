package com.improving.koindi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.improving.koindi.databinding.MainFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel{
        parametersOf(findNavController())
    }
     private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater)
        val view = binding.root

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.getFilmes()
        viewModel.filmesLiveData.observe(viewLifecycleOwner, {
            binding.textViewFilme.text = it[0].titulo
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}