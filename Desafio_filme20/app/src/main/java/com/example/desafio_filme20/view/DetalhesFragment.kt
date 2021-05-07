package com.example.desafio_filme20.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.desafio_filme20.R
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.viewmodel.FavoriteViewModel
import com.squareup.picasso.Picasso


class DetalhesFragment : Fragment() {

    private lateinit var detailsViewModel: FavoriteViewModel
    private val args: DetalhesFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_details, container, false)
        val textView: TextView = root.findViewById(R.id.title_details)
        val film: Film = args.filme
//        detailsViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = film.original_title

        val bundle = arguments
        if (bundle != null){
            val filmeParcela = bundle.getParcelable<Film>("filme")
            if (filmeParcela != null) {
                loadDetails(filmeParcela, root)
            }
            //setando os dados assim que recebo o objeto
        }

//        })

        return root
    }
    private fun loadDetails(film: Film, root: View){
        val titleDetails: TextView = root.findViewById(R.id.title_details)
        val posterDetails: ImageView = root.findViewById(R.id.image_poster_details)
        val releaseDate: TextView = root.findViewById(R.id.year_details)
        val overView: TextView = root.findViewById(R.id.overViewDetails)
        val baseUrlFilme = "https://image.tmdb.org/t/p/w200"

        titleDetails.setText(film.original_title)
        Picasso.get().load(baseUrlFilme+film.poster_path).into(posterDetails)
        releaseDate.setText("Release date: "+film.release_date)
        overView.setText("Overview: "+film.overview)


    }

}