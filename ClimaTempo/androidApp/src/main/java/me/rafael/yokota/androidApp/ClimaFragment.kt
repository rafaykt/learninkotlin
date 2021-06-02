package me.rafael.yokota.androidApp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.collect
import me.rafael.yokota.androidApp.databinding.ClimaFragmentBinding
import me.rafael.yokota.androidApp.util.SharedPreferencesUtil
import me.rafael.yokota.shared.model.Coord
import me.rafael.yokota.shared.model.Result
import me.rafael.yokota.shared.viewmodel.ClimaViewModel
import java.util.prefs.Preferences

class ClimaFragment : Fragment() {
    private val viewModel = ClimaViewModel()

    private var _binding: ClimaFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var locationPermissionGranted = false
    private var lastKnownLocation: Location? = null

    private var sharedPreferencesUtil= SharedPreferencesUtil

    private var coordenadas: Coord? = null

    private lateinit var dataStore: DataStore<Preferences>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            coordenadas = sharedPreferencesUtil.getCoordenadas(requireContext())

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        _binding = ClimaFragmentBinding.inflate(inflater, container, false)


        val view = binding.root
        getLocationPermission()
        binding.buttonLatlong.setOnClickListener {
            getLocationPermission()
            if(checkLocationPermission()) {
                getDeviceLocation()
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.weatherNow.collect {
                println(it.toString())
                if (it.coord.lat != 0.0 && it.coord.lon != 0.0) fillScreen(it)
                else if(coordenadas?.lat == 0.0 && coordenadas?.lon == 0.0) clearScreen()
                else{
                    clearScreen()
                    viewModel.setCoords(coordenadas?.lat!!, coordenadas?.lon!!)
                    getDeviceLocation()
                }
            }
        }

    }

    private fun checkLocationPermission(): Boolean{
        return (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        )
    }

    private fun getLocationPermission() {
            if(checkLocationPermission()){
                locationPermissionGranted = true
            } else {
                requestPermissions(
                    (arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)),
                    targetRequestCode
                )
                println("LOCATION PERMISSION : ${checkLocationPermission()}")
            }
    }

    private fun getDeviceLocation(): Coord? {
        var locationDevice: Coord? = null
        try {
            if (locationPermissionGranted) {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                    sharedPreferencesUtil.setCoordenadas(requireContext(),location?.latitude!!, location?.longitude!!)
                    println("lat: ${location?.latitude}long: ${location?.longitude}")
                    lifecycleScope.launchWhenStarted {
                        viewModel.getClimaTempo(location?.latitude!!, location?.longitude!!)
                            .toString()
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
        return locationDevice
    }

    private fun fillScreen(result: Result) {
        binding.tvCityname.text = result.name
        binding.tvTemperature.text = " ${String.format("%.1f", (result.main.temp - 273.15))}ºC "
        binding.tvDescription.text = result?.weather[0].description
        binding.latitude.text = "Latitude: ${result.coord.lat.toString()}"
        binding.longitude.text = "Longitude: ${result.coord.lon.toString()}"
    }

    private fun clearScreen() {
        binding.tvCityname.text = ""
        binding.tvTemperature.text = ""
        binding.tvDescription.text = ""
        binding.latitude.text = ""
        binding.longitude.text = ""
    }

//    private fun getApiData(lat: Double, lon: Double){
//        lifecycleScope.launchWhenStarted {
//            viewModel.getClimaTempo(location?.latitude!!, location?.longitude!!)
//                .toString()
//        }
//        if (location != null) {
//            if (locationDevice != null) {
//                locationDevice.lat = location.latitude
//                locationDevice.lon = location.longitude
//            }
//        }
//    }
}
