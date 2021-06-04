package me.rafael.yokota.androidApp

import adapter.ClimaAdapter
import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.rafael.yokota.androidApp.databinding.ClimaFragmentBinding
import me.rafael.yokota.androidApp.util.SharedPreferencesUtil
import me.rafael.yokota.shared.constants.Constants
import me.rafael.yokota.shared.model.Coord
import me.rafael.yokota.shared.model.Result
import me.rafael.yokota.shared.viewmodel.ClimaViewModel

class ClimaFragment : Fragment(), EasyPermissions.PermissionCallbacks {
    private val viewModel = ClimaViewModel()

    private var _binding: ClimaFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var locationPermissionGranted = false

    private var sharedPreferencesUtil = SharedPreferencesUtil

    private var coordenadas: Coord? = null
    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

    companion object {
        const val REQUEST_CODE_LOCATION = 99
    }

    private val mAdapter = ClimaAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        coordenadas = sharedPreferencesUtil.getCoordenadas(requireContext())

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)


        /*recycler*/
        _binding = ClimaFragmentBinding.inflate(inflater,container,false)
        binding.rowItemWeather.layoutManager =LinearLayoutManager(context)
        binding.rowItemWeather.adapter = mAdapter
        /*recycler*/




        val view = binding.root
        if (!hasLocationPermission())
            requestLocationPermission()
        binding.buttonLatlong.setOnClickListener {
            if (hasLocationPermission()) {
                getDeviceLocation()
            } else {
                requestLocationPermission()
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.weatherNow.collect {
                if (it.coord.lat != 0.0 && it.coord.lon != 0.0) fillScreen(it)
                else if (coordenadas?.lat == 0.0 && coordenadas?.lon == 0.0) clearScreen()
                else {
                    clearScreen()
                    getDeviceLocation()
                    val d = ""
                }
            }
            lifecycleScope.launch {
                viewModel.oneCallWeather.collect{
                    mAdapter.updateList(listOf(it.daily[0],it.daily[1]))
                    mAdapter.notifyDataSetChanged()
                    val s = ""
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }

    private fun hasLocationPermission() =
        EasyPermissions.hasPermissions(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "Esta aplicação necessita saber da sua localização",
            REQUEST_CODE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }


    private fun getDeviceLocation() {
        try {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->
                sharedPreferencesUtil.setCoordenadas(
                    requireContext(),
                    location?.latitude!!,
                    location.longitude
                )
                lifecycleScope.launchWhenStarted {
                    viewModel.getClimaTempo(location.latitude, location.longitude)
                    val s = ""
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillScreen(result: Result) {
        binding.tvCityname.text = result.name
        binding.tvTemperature.text = " ${String.format("%.1f", (result.main.temp - 273.15))}ºC "
        binding.tvDescription.text = result.weather[0].description
        binding.latitude.text = "Latitude: ${result.coord.lat}"
        binding.longitude.text = "Longitude: ${result.coord.lon}"
        Picasso.get().load(Constants.API.basUrlIcon+result.weather[0].icon+"@2x.png").into(binding.weatherIcon);
//        println(Constants.API.basUrlIcon+result.weather[0].icon+"@2x.png")
    }

    private fun clearScreen() {
        binding.tvCityname.text = ""
        binding.tvTemperature.text = ""
        binding.tvDescription.text = ""
        binding.latitude.text = ""
        binding.longitude.text = ""

    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestLocationPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        getDeviceLocation()
    }


}
