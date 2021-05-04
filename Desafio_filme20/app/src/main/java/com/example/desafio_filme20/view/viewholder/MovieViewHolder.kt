package com.example.desafio_filme20.view.viewholder

import android.app.AlertDialog
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.service.listener.TaskListener
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.service.model.TaskModel
import com.example.desafio_filme20.service.repository.PriorityRepository
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class MovieViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private var mTextTitulo: TextView = itemView.findViewById(R.id.title_movie)
    private var mTextOverview: TextView = itemView.findViewById(R.id.overview_movie)
    private var mPoster: ImageView = itemView.findViewById(R.id.poster_movie)
    private val baseUrlFilme = "https://image.tmdb.org/t/p/w200"
    /**
     * Atribui valores aos elementos de interface e também eventos
     */
    fun bindData(film: Film) {

        this.mTextTitulo.text = film.original_title
        this.mTextOverview.text = film.overview
        Picasso.get().load(baseUrlFilme+film.poster_path).into(mPoster)


        // Eventos
        /*mTextDescription.setOnClickListener { listener.onListClick(task.id) }
        mImageTask.setOnClickListener {
            if(task.complete){
                listener.onUndoClick(task.id)
            }else{
                listener.onCompleteClick(task.id)
            }
        }

        mTextDescription.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_de_tarefa)
                .setMessage(R.string.remover_tarefa)
                .setPositiveButton(R.string.sim) { dialog, which ->
                    listener.onDeleteClick(task.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()
            true
        }*/

    }

}