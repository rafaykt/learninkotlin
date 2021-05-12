package com.example.desafio_filme20.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.desafio_filme20.R
import com.example.desafio_filme20.databinding.FragmentDetailsBinding
import com.example.desafio_filme20.service.listeners.MovieListener
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.viewmodel.DetalhesViewModel
import com.example.desafio_filme20.viewmodel.FavoriteViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.view.*


class DetalhesFragment : Fragment() {

    private lateinit var detailsViewModel: DetalhesViewModel
    private val args: DetalhesFragmentArgs by navArgs()
    private lateinit var mListener: MovieListener

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        detailsViewModel =
            ViewModelProvider(this).get(DetalhesViewModel::class.java)

        val bundle = arguments
        if (bundle != null) {
            val filmeParcela = bundle.getParcelable<Film>("filme")
            if (filmeParcela != null) {
                loadDetails(filmeParcela)
            }
            //setando os dados assim que recebo o objeto
        }
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadDetails(film: Film) {
        var baseUrlFilme = "https://image.tmdb.org/t/p/w500"
        binding.titleDetails.text = film.original_title
        binding.yearDetails.text = "Release: ${film.release_date}"
        binding.overViewDetails.text = film.overview
        Picasso.get().load(baseUrlFilme + film.poster_path).into(binding.imagePosterDetails)

        if (film.favorite) {
            binding.iconFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.iconFavorite.setImageResource(R.drawable.ic_not_favorite)
        }

        binding.iconFavorite.setOnClickListener {
            if (film.favorite) {
                binding.iconFavorite.setImageResource(R.drawable.ic_not_favorite)
                detailsViewModel.removeFromFavorites(film)


            } else {
                detailsViewModel.addToFavorites(film)
                binding.iconFavorite.setImageResource(R.drawable.ic_favorite)

            }
        }


    }

}