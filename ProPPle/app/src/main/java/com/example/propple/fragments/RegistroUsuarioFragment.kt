package com.example.propple.fragments


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.text.InputType
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.api.UserClient.Sign
import com.example.propple.databinding.RegistroUsuarioFragmentBinding
import com.example.propple.viewModel.RegistroUsuarioViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar


class RegistroUsuarioFragment : Fragment() {

    private lateinit var v : View
    private lateinit var binding : RegistroUsuarioFragmentBinding
    private lateinit var viewModel: RegistroUsuarioViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0
    private lateinit var date : String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.registro_usuario_fragment, container, false)
        binding = RegistroUsuarioFragmentBinding.bind(v)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        return v
    }

    private fun getCurrentLocation() {

        if(checkPermisison()){
            if(isLocationEnable())
            {
                //final latitude and longitude
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions()
                    return
                }
                fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                    val location: Location?=task.result
                    if (location==null){
                        Toast.makeText(requireContext(),"Null recived",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(),"get Success",Toast.LENGTH_SHORT).show()
                        latitude=location.latitude
                        longitude=location.longitude
                        binding.InDirecion.setText(latitude.toString()+","+longitude.toString())
                    }
                }

            }else
            {
                // abrir ajustes para acitvar localizacion
                Toast.makeText(requireContext(),"Turn on location",Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }

        }else{
            //perdir permisos
            requestPermissions()
        }

    }

    private fun requestPermissions(){
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    private fun isLocationEnable(): Boolean {
        val locationManager : LocationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkPermisison(): Boolean {
        if(ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    companion object{
        private const val PERMISSION_REQUEST_ACCESS_LOCATION=100
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== PERMISSION_REQUEST_ACCESS_LOCATION){
            if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(requireContext(),"Granted",Toast.LENGTH_SHORT).show()
                getCurrentLocation()
            }else{
                Toast.makeText(requireContext(),"Denied",Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onStart() {
        super.onStart()

        binding.btnVerContra1.setOnClickListener {
            if (binding.InContrasenia1.inputType==129){
                binding.InContrasenia1.inputType=1
            }else if(binding.InContrasenia1.inputType==1) {
                binding.InContrasenia1.inputType=129
            }else{
                binding.InContrasenia1.inputType=129
            }

        }
        binding.btnVerContra2.setOnClickListener {
            if (binding.InContrasenia2.inputType==129){
                binding.InContrasenia2.inputType=1
            }else if(binding.InContrasenia1.inputType==1){
                binding.InContrasenia2.inputType=129
            }else{
                binding.InContrasenia2.inputType=129
            }

        }
        binding.btnUbicacion.setOnClickListener {  getCurrentLocation()}

        binding.btnRegistrarUsuario.setOnClickListener {
            if (binding.InContrasenia1.text.toString() != binding.InContrasenia2.text.toString()){
                Snackbar.make(v,"Ingrese nuevamente la contraseña", Snackbar.LENGTH_SHORT).show()
            }else{
                val userAux : Sign
                userAux = Sign(
                    "0",
                    binding.InAlias.text.toString(),
                    binding.InDirecion.text.toString(),
                    latitude,
                    longitude,
                    binding.InMail.text.toString(),
                    binding.InTelefono.text.toString(),
                    "",
                    binding.InApellido.text.toString(),
                    binding.InNombre.text.toString(),
                    binding.InContrasenia1.text.toString(),
                    binding.InFechaDeNacimiento.text.toString(),
                    true
                )
                Log.i("hola", userAux.toString())
                viewModel.sign(userAux)
                val action = RegistroUsuarioFragmentDirections.actionRegistroUsuarioFragmentToValidacionDeCuentaFragment()
                v.findNavController().navigate(action)
            }
        }

        binding.btnDate1.setOnClickListener { showDatePickerDialog() }
    }


    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        datePicker.show(fragmentManager, "RegistroUsuarioFragment")
    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.InFechaDeNacimiento.setText("$day / $month / $year")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistroUsuarioViewModel::class.java)
    }
}