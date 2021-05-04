package com.example.desafilme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafilme.R
import com.example.desafilme.repository.model.Movie

class MovieAdapter : ListAdapter<Movie, MyViewHolder>(Companion) {

    private var myList = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_recycler_view_popular_list, parent, false))
    }

    override fun getItemCount(): Int {
        myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMovie = getItem(position)

    }


    fun setData( newList: List<Movie> ){
        myList = newList
        notifyDataSetChanged()
    }

}

class MyViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)