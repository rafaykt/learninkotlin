package com.example.filemanipulator.view

import android.R.attr
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filemanipulator.databinding.FragmentHomeBinding
import com.example.filemanipulator.service.listener.ActionListener
import com.example.filemanipulator.service.model.Funcionario
import com.example.filemanipulator.view.viewholder.HomeAdapter
import com.example.filemanipulator.viewmodel.HomeViewModel
import kotlinx.coroutines.coroutineScope


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    val mAdapter = HomeAdapter()
    private lateinit var mListener : ActionListener
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        homeViewModel.getFuncionarioList()

        binding.rvFuncionario.layoutManager = LinearLayoutManager(context)
        binding.rvFuncionario.adapter=mAdapter

        createListeners()
        observe()

        return root
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1000) {
            val uri: Uri? = data?.getData()
            if (uri != null) {
                homeViewModel.readFile(uri)
            }
            Toast.makeText(context, uri.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
        _binding = null
    }

    private fun observe() {
        homeViewModel.funcionarioList.observe(viewLifecycleOwner,{
            mAdapter.updateList(it)
        })
    }

    private fun createListeners(){
        binding.filePickerButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(intent, 1000)
        }

        binding.createButton.setOnClickListener{
            val directions = HomeFragmentDirections.actionNavigationHomeToFormFuncionario(null)
            view?.findNavController()?.navigate(directions)
        }

        mListener = object: ActionListener{
            override fun showDetails(funcionario: Funcionario) {
                val directions = HomeFragmentDirections.actionNavigationHomeToFormFuncionario(funcionario)
                view?.findNavController()?.navigate(directions)
            }

            override fun deleteFunc(funcionario: Funcionario) {
                homeViewModel.deleteFuncionario(funcionario)
            }
        }
    }


}