package com.example.propple.fragments.clientePrestador

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.propple.R
import com.example.propple.databinding.FormularioReservaFragmentBinding
import com.example.propple.fragments.cliente.DatePickerFragment
import com.example.propple.shared.ProPPle
import com.example.propple.utils.GoogleMaps
import com.example.propple.utils.InputFieldValidator
import com.example.propple.utils.imgController
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.viewModel.FormularioReservaViewModel
import java.lang.Double.parseDouble

class FormularioReservaFragment : Fragment() {

    companion object {
        fun newInstance() = FormularioReservaFragment()
    }

    private lateinit var v : View
    private lateinit var binding : FormularioReservaFragmentBinding
    private lateinit var viewModel: FormularioReservaViewModel
    private lateinit var btnEnviarReserva : Button
    //private lateinit var fabVolverReservas1 : FloatingActionButton

    private lateinit var autocompleteSupportFragment1:AutocompleteSupportFragment
    private var direccion: String=""
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.formulario_reserva_fragment, container, false)
        binding = FormularioReservaFragmentBinding.bind(v)
        btnEnviarReserva = v.findViewById(R.id.btnEnviarReserva)
        //fabVolverReservas1 = v.findViewById(R.id.fabVolverReservas1)

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

    override fun onStart() {
        super.onStart()
        binding.InFechaDeNacrimiento.setOnClickListener { showDatePickerDialog() }

        /*fabVolverReservas1.setOnClickListener {
            val action = FormularioReservaFragmentDirections.actionFormularioReservaFragmentToReservasFragment2()
            v.findNavController().navigate(action)
        }*/

        binding.textDirecion.setOnClickListener {
            val root: View = autocompleteSupportFragment1.requireView()
            root.findViewById<View>(com.google.android.libraries.places.R.id.places_autocomplete_search_input).performClick()
        }
    }
    fun setAvatar(img64:String){
        if (ProPPle.prefs.getUrlImageString()!="")
            imgController.getImgUrl(
                img64,
                v.context,
                v.findViewById<ImageView>(R.id.btnAvatar)
            )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormularioReservaViewModel::class.java)
        var trx = FormularioReservaFragmentArgs.fromBundle(requireArguments()).trx
        setAvatar(trx.url_download_image)
        btnEnviarReserva.setOnClickListener {
            if(verificarCamposVacios()) {
                Snackbar.make(v, "Los campos con * son obligatorios", Snackbar.LENGTH_SHORT).show()
            } else if(!InputFieldValidator.esNumerico(
                    binding.inPrecioHora,
                    binding.txvinPrecioHora,
                    1979711488
                )
            ) {
                Snackbar.make(v, "El precio debe ser númerico", Snackbar.LENGTH_SHORT).show()
            }
            else {
                viewModel.enviarFormulario(binding.InFechaDeNacrimiento.text.toString(),trx.id_transaccion,direccion,latitude,longitude,parseDouble(binding.inPrecioHora.text.toString()))
                val action = FormularioReservaFragmentDirections.actionFormularioReservaFragmentToReservasFragment22()
                v.findNavController().navigate(action)
                Snackbar.make(v, "Reserva enviada a Cliente para confirmar", Snackbar.LENGTH_SHORT).show()
            }
        }

    }
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        datePicker.show(fragmentManager, "RegistroUsuarioFragment")
    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.InFechaDeNacrimiento.setText("$day / $month / $year")
    }
    private fun verificarCamposVacios(): Boolean {
        var campoVacio = false
        var txvDefaultColor = 1979711488
        if (InputFieldValidator.esCampoVacio(binding.InFechaDeNacrimiento, binding.txvInFechaDeNacrimientoDP2, txvDefaultColor) && !campoVacio) campoVacio = true
        //if (InputFieldValidator.esCampoVacio(binding.inTituloServicio, binding.txvinTituloServicio, txvDefaultColor) && !campoVacio) campoVacio = true
        //if (InputFieldValidator.esCampoVacio(binding.inDescripcionServicio, binding.txvinDescripcionServicio, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.inPrecioHora, binding.txvinPrecioHora, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio2(direccion, binding.txvinDireccionLaboral, txvDefaultColor) && !campoVacio) campoVacio = true
        return campoVacio
    }

}