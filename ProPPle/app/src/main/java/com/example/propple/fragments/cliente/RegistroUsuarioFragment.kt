package com.example.propple.fragments.cliente


import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.api.UserClient.Sign
import com.example.propple.databinding.RegistroUsuarioFragmentBinding
import com.example.propple.utils.GoogleMaps
import com.example.propple.viewModel.cliente.RegistroUsuarioViewModel
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
    private var googleMaps: GoogleMaps=GoogleMaps()

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.registro_usuario_fragment, container, false)
        binding = RegistroUsuarioFragmentBinding.bind(v)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        return v
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
                googleMaps.getCurrentLocation(requireActivity(),requireContext(),fusedLocationClient)
            }else{
                Toast.makeText(requireContext(),"Denied",Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onStart() {
        super.onStart()
        googleMaps.lat.observe(viewLifecycleOwner, Observer {
            latitude=it
        })
        googleMaps.lon.observe(viewLifecycleOwner, Observer {
            longitude=it
        })

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
        binding.btnUbicacion.setOnClickListener {
            googleMaps.getCurrentLocation(requireActivity(),requireContext(),fusedLocationClient)
            googleMaps.dir.observe(viewLifecycleOwner, Observer {
                binding.InDirecion.setText(it)
            })
        } //

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
                    "Sin especificar",
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
