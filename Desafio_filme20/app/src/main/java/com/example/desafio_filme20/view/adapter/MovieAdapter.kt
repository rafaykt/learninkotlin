package com.example.desafio_filme20.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.service.listener.TaskListener
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.view.viewholder.MovieViewHolder

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var mList: List<Film> = arrayListOf()
    private lateinit var mListener: TaskListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.row_list_films, parent, false)
        return MovieViewHolder(item)
    }

    override fun getItemCount(): Int {
//        return 0
        return mList.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun attachListener(listener: TaskListener) {
        mListener = listener
    }

    fun updateListener(list: List<Film>){
        mList= list
        notifyDataSetChanged()
    }

}