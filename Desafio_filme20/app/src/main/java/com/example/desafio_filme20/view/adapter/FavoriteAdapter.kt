package com.example.desafio_filme20.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.service.listeners.MovieListener
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.view.viewholder.FavoriteViewHolder
import com.example.desafio_filme20.view.viewholder.MovieViewHolder

class FavoriteAdapter : RecyclerView.Adapter<FavoriteViewHolder>() {

    private var mList: List<Film> = arrayListOf()
    private lateinit var mListener: MovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.row_list_films, parent, false)
        return FavoriteViewHolder(item, mListener)
    }

    override fun getItemCount(): Int {
//        return 0
        return mList.count()
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
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