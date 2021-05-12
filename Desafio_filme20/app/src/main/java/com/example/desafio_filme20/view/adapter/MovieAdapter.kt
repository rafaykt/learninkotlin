package com.example.desafio_filme20.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.databinding.RowListFilmsBinding
import com.example.desafio_filme20.service.listeners.MovieListener
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.view.viewholder.MovieViewHolder

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var mList: List<Film> = arrayListOf()
    private lateinit var mListener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val rowListFilmsBinding = RowListFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(rowListFilmsBinding, mListener)
    }

    override fun getItemCount(): Int {
//        return 0
        return mList.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(mList[position])

    }

    fun attachListener(listener: MovieListener) {
        mListener = listener
    }

    fun updateListener(list: List<Film>){
        mList= list
        notifyDataSetChanged()
    }

}