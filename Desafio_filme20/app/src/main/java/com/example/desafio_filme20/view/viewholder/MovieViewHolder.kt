package com.example.desafio_filme20.view.viewholder

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.service.listeners.MovieListener
import com.example.desafio_filme20.service.model.Film
import com.squareup.picasso.Picasso

class MovieViewHolder(itemView: View, val listener: MovieListener, var filme: Film? = null) :
    RecyclerView.ViewHolder(itemView) {

    private var mTextTitulo: TextView = itemView.findViewById(R.id.title_movie)
    private var mTextOverview: TextView = itemView.findViewById(R.id.overview_movie)
    private var mPoster: ImageView = itemView.findViewById(R.id.poster_movie)
    private var mBody: ConstraintLayout = itemView.findViewById(R.id.body)
    private val baseUrlFilme = "https://image.tmdb.org/t/p/w200"
    private val mFavoriteStar: ImageButton = itemView.findViewById(R.id.btn_favorite_film)
    /**
     * Atribui valores aos elementos de interface e também eventos
     */
    fun bindData(film: Film) {

        this.mTextTitulo.text = film.original_title
        this.mTextOverview.text = film.overview
        Picasso.get().load(baseUrlFilme+film.poster_path).into(mPoster)


        // Eventos
        mBody.setOnClickListener { listener.onListClick(film) }
        mFavoriteStar.setOnClickListener {
            if(film.favorite){
                listener.undoFavorite(film)
            }else{
                listener.onFavorite(film)
            }
        }
//
//        mTextDescription.setOnLongClickListener {
//            AlertDialog.Builder(itemView.context)
//                .setTitle(R.string.remocao_de_tarefa)
//                .setMessage(R.string.remover_tarefa)
//                .setPositiveButton(R.string.sim) { dialog, which ->
//                    listener.onDeleteClick(task.id)
//                }
//                .setNeutralButton(R.string.cancelar, null)
//                .show()
//            true
//        }

    }

}