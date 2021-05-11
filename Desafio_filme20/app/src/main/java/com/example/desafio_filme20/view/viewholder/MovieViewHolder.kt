package com.example.desafio_filme20.view.viewholder

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_filme20.R
import com.example.desafio_filme20.databinding.RowListFilmsBinding
import com.example.desafio_filme20.service.listeners.MovieListener
import com.example.desafio_filme20.service.model.Film
import com.squareup.picasso.Picasso

class MovieViewHolder(private val binding: RowListFilmsBinding, val listener: MovieListener, var filme: Film? = null) :
    RecyclerView.ViewHolder(binding.root) {

    private val baseUrlFilme = "https://image.tmdb.org/t/p/w200"

    /**
     * Atribui valores aos elementos de interface e também eventos
     */
    fun bindData(film: Film) {

        binding.titleMovie.text = film.original_title
        binding.overviewMovie.text = film.overview
        Picasso.get().load(baseUrlFilme+film.poster_path).into(binding.posterMovie)
        if(film.favorite){
            binding.btnFavoriteFilm.setImageResource(R.drawable.ic_favorite)
        }else{
            binding.btnFavoriteFilm.setImageResource(R.drawable.ic_not_favorite)
        }

        // Eventos

        binding.body.setOnClickListener { listener.onListClick(film) }
        binding.btnFavoriteFilm.setOnClickListener {
            if(film.favorite){
                listener.undoFavorite(film)
                binding.btnFavoriteFilm.setImageResource(R.drawable.ic_not_favorite)
            }else{
                listener.onFavorite(film)
                binding.btnFavoriteFilm.setImageResource(R.drawable.ic_favorite)
            }
        }


    }




}