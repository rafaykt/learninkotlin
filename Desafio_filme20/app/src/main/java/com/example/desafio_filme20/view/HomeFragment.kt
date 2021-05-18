package com.example.desafio_filme20.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.databinding.FragmentHomeBinding
import com.example.desafio_filme20.service.listeners.MovieListener
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.view.adapter.MovieAdapter
import com.example.desafio_filme20.viewmodel.HomeViewModel
import okhttp3.internal.notify

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mListener : MovieListener
    private val mAdapter = MovieAdapter()

    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        /*recycler*/
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rvListFilms.layoutManager = LinearLayoutManager(context)
        binding.rvListFilms.adapter=mAdapter
        /*recycler*/

        /*Listener*/
        mListener = object : MovieListener{
            override fun onListClick(filme: Film) {
            val directions = HomeFragmentDirections.actionNavigationHomeToNavigationDetails2(filme)
                view?.findNavController()?.navigate(directions)
            }

            override fun onFavorite(filme: Film) {
                homeViewModel.addToFavorites(filme)
                filme.favorite=true
            }

            override fun undoFavorite(filme: Film) {
                homeViewModel.removeFromFavorites(filme)
                filme.favorite=false
            }
        }

        /*Listener*/
        observe()

        return binding.root
    }

    override fun onResume(){
        super.onResume()
        mAdapter.attachListener(mListener)
        homeViewModel.listPopularFilms()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModelStore.clear()
    }

    private fun observe() {
        homeViewModel.list.observe(viewLifecycleOwner, Observer {
            mAdapter.notifyDataSetChanged()
                mAdapter.updateListener(it)
        })

    }
}