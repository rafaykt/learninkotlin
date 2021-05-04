package com.example.desafilme.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.desafilme.R
import com.example.desafilme.adapter.MovieAdapter
import com.example.desafilme.databinding.FragmentHomeBinding
import com.example.desafilme.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.zip.Inflater

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val homeViewModel: HomeViewModel by lazy{
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    var listView: ListView? = null
    var movies= mutableListOf<String>()
//    var movieAdapter: ArrayAdapter<String>? = null


    @SuppressLint("CheckResult")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val api = MovieRepository()
        api.loadMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ movie ->
                movies?.add(movie.original_title)
            }, { e ->
                e.printStackTrace()
            },{
                movieAdapter?.notifyDataSetChanged()
            })

        binding.rvPopularList.adapter= movieAdapter

        /*Todo*/
//        criar o adapter

        return binding.root
    }


    override fun onDestroyView() {
//        avoiding memory leak
        _binding=null
        super.onDestroyView()
    }


}