package com.example.filemanipulator.view.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filemanipulator.databinding.RowFuncionarioBinding
import com.example.filemanipulator.service.listener.ActionListener
import com.example.filemanipulator.service.model.Funcionario

class HomeAdapter: RecyclerView.Adapter<HomeViewHolder>() {
    private var mList : List<Funcionario> = arrayListOf()
    private lateinit var mListener: ActionListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val rowFuncionarioBinding = RowFuncionarioBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return HomeViewHolder(rowFuncionarioBinding, mListener)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun attachListener(listener: ActionListener){
        mListener = listener
    }

    fun updateListener(list: List<Funcionario>){
        mList = list
        notifyDataSetChanged()
    }
}