package com.example.desafio_filme20.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.service.listeners.MovieListener
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.view.adapter.MovieAdapter
import com.example.desafio_filme20.viewmodel.DashboardViewModel
import com.example.desafio_filme20.viewmodel.FavoriteViewModel
import com.example.desafio_filme20.viewmodel.HomeViewModel

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var mListener : MovieListener
    private val mAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)

        /*recycler*/
        val recycler = root.findViewById<RecyclerView>(R.id.rv_listFilms)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter
        /*recycler*/

        /*Listener*/
        mListener = object : MovieListener {
            override fun onListClick(filme: Film) {
//                Toast.makeText(context, "Filme: ${filme.title}", Toast.LENGTH_SHORT).show()
//                val directions = HomeFragmentDirections.actionNavigationHomeToNavigationDetails2(filme)
//                view?.findNavController()?.navigate(directions)
                val directions = FavoriteFragmentDirections.actionNavigationFavoritesToNavigationDetails2(filme)
                view?.findNavController()?.navigate(directions)
            }

            override fun onFavorite(filme: Film) {

            }

            override fun undoFavorite(filme: Film) {
                favoriteViewModel.removeFromFavorites(filme)
            }
        }
        /*Listener*/
        observe()

        return root
    }

    override fun onResume(){
        super.onResume()
        mAdapter.attachListener(mListener)
        favoriteViewModel.getListFavoriteFilms()
    }

    private fun observe() {
        favoriteViewModel.list.observe(viewLifecycleOwner, Observer {
            mAdapter.updateListener(it)
        })

    }

}