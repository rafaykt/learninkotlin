package com.example.desafilme.adapter

import BookAdapterForFilm
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafilme.repository.model.Movie

@BindingAdapter(value = ["setBooks", "setBookListener"])
fun RecyclerView.setRowBook(movies: List<Movie>?, listener: ItemClickListener<Movie>?) {
    if (movies != null && listener != null) {
        val bookAdapter = BookAdapterForFilm(listener)
        bookAdapter.submitList(movies)

        adapter = bookAdapter
    }
}