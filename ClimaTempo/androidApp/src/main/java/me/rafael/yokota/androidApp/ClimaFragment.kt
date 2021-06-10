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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.rafael.yokota.androidApp.databinding.ClimaFragmentBinding
import me.rafael.yokota.androidApp.util.SharedPreferencesUtil
import me.rafael.yokota.shared.constants.Constants
import me.rafael.yokota.shared.model.Coord
import me.rafael.yokota.shared.model.Result
import me.rafael.yokota.shared.viewmodel.ClimaViewModelShared

class ClimaFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    private val viewModel = ClimaViewModelShared()
    private val mAdapter = ClimaAdapter()
    private var sharedPreferencesUtil = SharedPreferencesUtil

    private var _binding: ClimaFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var coordenadas: Coord? = null

    companion object {
        const val REQUEST_CODE_LOCATION = 99
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*recycler*/
        _binding = ClimaFragmentBinding.inflate(inflater, container, false)
        binding.rowItemWeather.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rowItemWeather.adapter = mAdapter
        /*recycler*/
        coordenadas = sharedPreferencesUtil.getCoordenadas(requireContext())
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        if (!hasLocationPermission()) requestLocationPermission()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.weatherNow.collect {
                if (it.coord.lat != 0.0 && it.coord.lon != 0.0) {
                    fillScreen(it)
                } else clearScreen()
            }
        }

        lifecycleScope.launch {
            viewModel.oneCallWeather.collect {
                mAdapter.updateList(it.daily)
                mAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (hasLocationPermission())
            getDeviceLocation()
    }

    override fun onStop() {
        super.onStop()
        viewModel.cancelCoroutines()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelCoroutines()
    }

    /* Easy permissions {*/
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

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestLocationPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(requireContext(), "Got permission", Toast.LENGTH_SHORT).show()
    }
    /*} Easy Permissions*/

    private fun getDeviceLocation() {
        try {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location ->
                sharedPreferencesUtil.setCoordenadas(
                    requireContext(),
                    location.latitude,
                    location.longitude
                )
                    viewModel.getClimaTempo(location.latitude, location.longitude)
                    viewModel.getOneCallData(location.latitude, location.longitude)
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
        Picasso.get().load(Constants.API.basUrlIcon + result.weather[0].icon + "@2x.png")
            .into(binding.weatherIcon);
    }

    private fun clearScreen() {
        binding.tvCityname.text = ""
        binding.tvTemperature.text = ""
        binding.tvDescription.text = ""
        binding.latitude.text = ""
        binding.longitude.text = ""
    }

}
