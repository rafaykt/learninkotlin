package com.example.desafio_filme20.view

import android.os.Bundle
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
import com.example.desafio_filme20.databinding.FragmentFavoritesBinding
import com.example.desafio_filme20.service.listeners.MovieListener
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.view.adapter.MovieAdapter
import com.example.desafio_filme20.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var mListener : MovieListener
    private val mAdapter = MovieAdapter()
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    {
        favoriteViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)


        /*recycler*/
        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        binding.rvListFilms.layoutManager = LinearLayoutManager(context)
        binding.rvListFilms.adapter = mAdapter
        /*recycler*/

        /*Listener*/
        mListener = object : MovieListener {
            override fun onListClick(filme: Film) {
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

        return binding.root
    }

    override fun onResume(){
        super.onResume()
        mAdapter.attachListener(mListener)
        favoriteViewModel.getListFavoriteFilms()
    }

    private fun observe() {
        favoriteViewModel.list.observe(viewLifecycleOwner, Observer {
            mAdapter.notifyDataSetChanged()
            mAdapter.updateListener(it)

        })

    }

}