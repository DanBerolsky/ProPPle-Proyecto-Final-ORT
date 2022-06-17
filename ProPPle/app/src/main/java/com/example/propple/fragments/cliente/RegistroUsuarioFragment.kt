package com.example.propple.fragments.cliente


import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.api.UserClient.Sign
import com.example.propple.databinding.RegistroUsuarioFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.utils.GoogleMaps
import com.example.propple.utils.InputFieldValidator
import com.example.propple.utils.InputFieldValidator.Companion.esEmail
import com.example.propple.utils.InputFieldValidator.Companion.esNumerico
import com.example.propple.utils.InputFieldValidator.Companion.esTelefono
import com.example.propple.utils.InputFieldValidator.Companion.noTieneNumerosNiSimbolos
import com.example.propple.utils.InputFieldValidator.Companion.noTieneSimbolos
import com.example.propple.viewModel.cliente.RegistroUsuarioViewModel
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.material.snackbar.Snackbar


class RegistroUsuarioFragment : Fragment() {

    private lateinit var v : View
    private lateinit var binding : RegistroUsuarioFragmentBinding
    private lateinit var viewModel: RegistroUsuarioViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0
    private lateinit var date : String

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    private var googleMaps: GoogleMaps = GoogleMaps()
    private lateinit var autocompleteSupportFragment1:AutocompleteSupportFragment
    private var direccion: String=""
    private lateinit var textView:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.registro_usuario_fragment, container, false)
        binding = RegistroUsuarioFragmentBinding.bind(v)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        prefs.clear()
        // Fetching API_KEY which we wrapped
        val applicationContext=requireContext()
        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = ai.metaData["keyValue"]
        val apiKey = value.toString()

        // Initializing the Places API
        // with the help of our API_KEY
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }

        // Initialize Autocomplete Fragments
        // from the main activity layout file
        autocompleteSupportFragment1 = (childFragmentManager.findFragmentById(R.id.autocomplete_fragment1) as AutocompleteSupportFragment?)!!
        // Information that we wish to fetch after typing
        // the location and clicking on one of the options
        autocompleteSupportFragment1.setPlaceFields(
            listOf(
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.LAT_LNG))

        // Display the fetched information after clicking on one of the options
        autocompleteSupportFragment1.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {

                // Text view where we will
                // append the information that we fetch

                // Information about the place
                val address = place.address
                if (address != null) {
                    direccion = address
                }
                //val phone = place.phoneNumber.toString()


                val latlng = place.latLng
                val latitude1 = latlng?.latitude
                val longitude1 = latlng?.longitude

                if (latitude1 != null) {
                    latitude=latitude1
                }
                if (longitude1 != null) {
                    longitude=longitude1
                }
                Toast.makeText(applicationContext,address, Toast.LENGTH_SHORT).show()
                binding.textDirecion.setText("${address}")
            }

            override fun onError(status: Status) {
                Toast.makeText(applicationContext,"Some error occurred", Toast.LENGTH_SHORT).show()
            }
        })
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
        /*binding.btnUbicacion.setOnClickListener {
            googleMaps.getCurrentLocation(requireActivity(),requireContext(),fusedLocationClient)
            googleMaps.dir.observe(viewLifecycleOwner, Observer {
                binding.InDirecion.setText(it)
            })
        } */

        binding.btnUbicacion.setOnClickListener { googleMaps.getCurrentLocation(requireActivity(),requireContext(),fusedLocationClient)
            googleMaps.dir.observe(viewLifecycleOwner, Observer {
                direccion=it
                //autocompleteSupportFragment1.setText(it)
                binding.textDirecion.setText(it)
            }) }

        binding.textDirecion.setOnClickListener {
            val root: View = autocompleteSupportFragment1.requireView()
            root.findViewById<View>(com.google.android.libraries.places.R.id.places_autocomplete_search_input).performClick()
        }


        binding.btnRegistrarUsuario.setOnClickListener {
            Log.i("ajdsklasj",direccion +"\n"+ latitude +"\n"+ longitude)
            if(verificarCamposVacios()) {
                Snackbar.make(v, "Los campos con * son obligatorios", Snackbar.LENGTH_SHORT).show()
            }
            else if (!noTieneNumerosNiSimbolos(binding.InNombre, binding.txvInNombre, 1979711488)){
                Snackbar.make(v, "El nombre debe contener letras únicamente", Snackbar.LENGTH_SHORT).show()
            }
            else if (!noTieneNumerosNiSimbolos(binding.InApellido, binding.txvInApellido, 1979711488)){
                Snackbar.make(v, "El apellido debe contener letras únicamente", Snackbar.LENGTH_SHORT).show()
            }
            else if (!noTieneSimbolos(binding.InAlias, binding.txvInAlias, 1979711488)){
                Snackbar.make(v, "El alias no puede contener símbolos", Snackbar.LENGTH_SHORT).show()
            }
            else if (!esEmail(binding.InMail, binding.txvInMail, 1979711488)){
                Snackbar.make(v, "Formato de email incorrecto", Snackbar.LENGTH_SHORT).show()
            }
            else if (!esNumerico(binding.InTelefono, binding.txvInTelefono, 1979711488)){
                Snackbar.make(v, "El teléfono debe contener números únicamente", Snackbar.LENGTH_SHORT).show()
            }
            else if (!esTelefono(binding.InTelefono, binding.txvInTelefono, 1979711488)){
                Snackbar.make(v, "Formato de teléfono incorrecto", Snackbar.LENGTH_SHORT).show()
            }
            else {
                if (binding.InContrasenia1.text.toString() != binding.InContrasenia2.text.toString()){
                    Snackbar.make(v,"Ingrese nuevamente la contraseña", Snackbar.LENGTH_SHORT).show()
                } else {
                    val userAux : Sign
                    userAux = Sign(
                        "0",
                        binding.InAlias.text.toString(),
                        direccion,
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
                        true,
                        ""
                    )
                    Log.i("hola", userAux.toString())
                    viewModel.sign(userAux)
                    val action = RegistroUsuarioFragmentDirections.actionRegistroUsuarioFragmentToValidacionDeCuentaFragment()
                    v.findNavController().navigate(action)
                }
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

    private fun verificarCamposVacios(): Boolean {
        var campoVacio = false
        var txvDefaultColor = 1979711488
        if (InputFieldValidator.esCampoVacio(binding.InNombre, binding.txvInNombre, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InApellido, binding.txvInApellido, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InMail, binding.txvInMail, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InAlias, binding.txvInAlias, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InFechaDeNacimiento, binding.txvInFechaDeNacimiento, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InTelefono, binding.txvInTelefono, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio2(direccion, binding.txvInDirecion, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InContrasenia1, binding.txvInContrasenia1, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InContrasenia2, binding.txvInContrasenia2, txvDefaultColor) && !campoVacio) campoVacio = true
        return campoVacio
    }

}