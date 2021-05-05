package com.example.desafio_filme20.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.desafio_filme20.R
import com.example.desafio_filme20.service.model.Film
import com.example.desafio_filme20.viewmodel.NotificationsViewModel



class DetalhesFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private val args: DetalhesFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)

        val film: Film = args.filme
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = film.original_title

            val bundle = arguments
            if (bundle != null){
                val film = bundle.getParcelable<Film>("filme")
                textView.setText(film?.original_title)
            }

        })

        return root
    }


}