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
import com.example.desafio_filme20.service.listeners.MovieListener
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.viewmodel.DetalhesViewModel
import com.example.desafio_filme20.viewmodel.FavoriteViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.view.*


class DetalhesFragment : Fragment() {

    private lateinit var detailsViewModel: DetalhesViewModel
    private val args: DetalhesFragmentArgs by navArgs()
    private lateinit var mListener : MovieListener




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel =
            ViewModelProvider(this).get(DetalhesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_details, container, false)

        val bundle = arguments
        if (bundle != null){
            val filmeParcela = bundle.getParcelable<Film>("filme")
            if (filmeParcela != null) {
                loadDetails(filmeParcela, root)
            }
            //setando os dados assim que recebo o objeto
        }

        return root
    }

    private fun loadDetails(film: Film, root: View){
        /*View Ids*/
        var titleDetails: TextView = root.findViewById(R.id.title_details)
        var posterDetails: ImageView = root.findViewById(R.id.image_poster_details)
        var releaseDate: TextView = root.findViewById(R.id.year_details)
        var overView: TextView = root.findViewById(R.id.overViewDetails)
        var baseUrlFilme = "https://image.tmdb.org/t/p/w500"
        var favoriteIcon : ImageButton = root.icon_favorite

        titleDetails.setText(film.original_title)
        Picasso.get().load(baseUrlFilme+film.poster_path).into(posterDetails)
        releaseDate.setText("Release date: "+film.release_date)
        overView.setText("Overview: "+film.overview)

        if(film.favorite){
            favoriteIcon.setImageResource(R.drawable.ic_favorite)
        }else{
            favoriteIcon.setImageResource(R.drawable.ic_not_favorite)
        }

        favoriteIcon.setOnClickListener{
            if(film.favorite){
                favoriteIcon.setImageResource(R.drawable.ic_not_favorite)
                detailsViewModel.removeFromFavorites(film)


            }else{
                detailsViewModel.addToFavorites(film)
                favoriteIcon.setImageResource(R.drawable.ic_favorite)

            }
        }



    }

}