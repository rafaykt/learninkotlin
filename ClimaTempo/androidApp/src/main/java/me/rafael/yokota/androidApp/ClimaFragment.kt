package me.rafael.yokota.androidApp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.PermissionRequest
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.rafael.yokota.androidApp.databinding.ClimaFragmentBinding
import me.rafael.yokota.shared.model.Coord
import me.rafael.yokota.shared.model.Result
import me.rafael.yokota.shared.viewmodel.ClimaViewModel
import kotlin.math.roundToLong


class ClimaFragment : Fragment() {

    private val viewModel = ClimaViewModel()

    private var _binding: ClimaFragmentBinding? = null
    private val binding get() = _binding!!

    /* Location */
    // The entry point to the Fused Location Provider.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.

    private var locationPermissionGranted = false

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private var lastKnownLocation: Location? = null

//    val coord


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ClimaFragmentBinding.inflate(inflater, container, false)
        val view = binding.root


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        binding.buttonLatlong.setOnClickListener {
            getLocationPermission()
            getDeviceLocation()
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        var tvv = binding.textViewClima

        lifecycleScope.launchWhenStarted {
            viewModel.weatherNow.collect {
//                viewModel.getClimaTempo(30.000, 30.000).toString()
                println(it.toString())
                if (it.coord.lat!=0.0 && it.coord.lon != 0.0) fillScreen(it)
                else clearScreen()

            }
        }

    }

    private fun getLocationPermission() {

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
        } else {
            requestPermissions(
                (arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)),
                targetRequestCode
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out kotlin.String>,
        grantResults: IntArray
    ): Unit {

    }

    private fun getDeviceLocation(): Coord? {
        var locationDevice: Coord? = null
        try {
            if (locationPermissionGranted) {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                    println("lat: ${location?.latitude}long: ${location?.longitude}")

//                    var tvv = binding.textViewClima
                    lifecycleScope.launchWhenStarted {
//                        viewModel.weatherNow.collect {
                        viewModel.getClimaTempo(location?.latitude!!, location?.longitude!!).toString()
//                            println(it.toString())
//                            tvv.text = it.toString()
//                        }

                    }

                    if (location != null) {
                        if (locationDevice != null) {
                            locationDevice.lat=location.latitude
                            locationDevice.lon = location.longitude
                        }
                    }
                }


//                binding.tvlaglong.text = locationResult.toString()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
        return locationDevice
    }

    private fun fillScreen(result: Result){
        binding.tvCityname.text = result.name
        binding.tvTemperature.text = " ${String.format("%.1f",(result.main.temp -273.15))}ºC "
        binding.tvDescription.text = result?.weather[0].description
        binding.latitude.text = "Latitude: ${result.coord.lat.toString()}"
        binding.longitude.text = "Longitude: ${result.coord.lon.toString()}"
    }

    private fun clearScreen(){
        binding.tvCityname.text = ""
        binding.tvTemperature.text = ""
        binding.tvDescription.text = ""
        binding.latitude.text = ""
        binding.longitude.text = ""
    }
}
