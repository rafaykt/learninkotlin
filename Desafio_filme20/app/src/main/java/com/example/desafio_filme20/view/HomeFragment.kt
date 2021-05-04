package com.example.desafio_filme20.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.view.adapter.MovieAdapter
import com.example.desafio_filme20.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val mAdapter = MovieAdapter()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.rv_listFilms)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter
        observe()

        return root
    }

    override fun onResume(){
        super.onResume()
//        mAdapter.attachListener(mListener)
        homeViewModel.listPopularFilms()
    }

    private fun observe() {
        homeViewModel.list.observe(viewLifecycleOwner, Observer {
                mAdapter.updateListener(it)
        })

    }
}