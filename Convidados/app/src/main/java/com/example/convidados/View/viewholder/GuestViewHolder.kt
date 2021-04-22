package com.example.convidados.View.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.Listeners.GuestListener
import com.example.convidados.R
import com.example.convidados.Service.Model.GuestModel
import kotlinx.android.synthetic.main.row_guest.view.*

class GuestViewHolder(itemView: View, private val listener: GuestListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener{
            listener.onClick(guest.id)
        }
    }


}