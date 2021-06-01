package me.rafael.yokota.androidApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import me.rafael.yokota.androidApp.databinding.ClimaFragmentBinding
import me.rafael.yokota.shared.viewmodel.ClimaViewModel

class ClimaFragment : Fragment() {

    private val viewModel = ClimaViewModel()

    private var _binding: ClimaFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val textv= view?.findViewById<TextView>(R.id.tv)

        _binding = ClimaFragmentBinding.inflate(inflater, container,false)
        val view = binding.root

        var tvv = binding.textViewClima
        tvv.text = "Climatempo"
//        lifecycleScope.launchWhenStarted {
//            viewModel.weatherNow.collect {
//                if (textv != null) {
//                    textv.setText(it.toString())
//                    tvv.text = it.toString()
//                }
//            }
//        }
        return view
    }

    override fun onStart() {
        super.onStart()

        var tvv = binding.textViewClima
//        binding.textViewClima.text = viewModel.getClimaTempo().toString()
        lifecycleScope.launchWhenStarted {
            viewModel.weatherNow.collect {
                      viewModel.getClimaTempo().toString()
                tvv.text = it.toString()
            }
        }

    }
}