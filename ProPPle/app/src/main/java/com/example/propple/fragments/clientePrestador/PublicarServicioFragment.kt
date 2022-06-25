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
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.propple.R
import com.example.propple.databinding.PublicarServicioFragmentBinding
import com.example.propple.utils.InputFieldValidator
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.fragments.PublicacionFragmentArgs
import com.ort.casodeusotest.viewModel.PublicarServicioViewModel

class PublicarServicioFragment : Fragment() {

    companion object {
        fun newInstance() = PublicarServicioFragment()
    }

    private lateinit var v : View
    private lateinit var binding : PublicarServicioFragmentBinding
    private lateinit var btnPublicar : Button
    private lateinit var fabVolverPublicacion1 : FloatingActionButton

    private lateinit var viewModel: PublicarServicioViewModel

    private lateinit var autocompleteSupportFragment1: AutocompleteSupportFragment
    private var direccion: String=""
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.publicar_servicio_fragment, container, false)
        binding = PublicarServicioFragmentBinding.bind(v)
        btnPublicar = v.findViewById(R.id.btnEnviarReserva)
        //fabVolverPublicacion1 = v.findViewById(R.id.fabVolverPublicacion1)


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

        /*fabVolverPublicacion1.setOnClickListener {
            val action = PublicarServicioFragmentDirections.actionPublicarServicioFragmentToPublicacionFragment(PublicarServicioFragmentArgs.fromBundle(requireArguments()).rubroPosition)
            v.findNavController().navigate(action)
        }*/

        binding.textDirecion.setOnClickListener {
            val root: View = autocompleteSupportFragment1.requireView()
            root.findViewById<View>(com.google.android.libraries.places.R.id.places_autocomplete_search_input).performClick()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicarServicioViewModel::class.java)
        val id = PublicacionFragmentArgs.fromBundle(requireArguments()).id


        btnPublicar.setOnClickListener {
            if(verificarCamposVacios()) {
                Snackbar.make(v, "Los campos con * son obligatorios", Snackbar.LENGTH_SHORT).show()
            } else if(!InputFieldValidator.esNumerico(
                    binding.InPrecioHora,
                    binding.txvInPrecioHoraPS,
                    1979711488
                )
            ) {
                Snackbar.make(v, "El precio debe ser númerico", Snackbar.LENGTH_SHORT).show()
            }
            else {
                viewModel.publicarServicio(id,direccion,latitude,longitude,binding.InPrecioHora.text.toString().toInt() ,binding.InDescripcionServicio.text.toString(),binding.InTituloServicio.text.toString())

            }
        }
        viewModel.status2.observe(viewLifecycleOwner, Observer {
            if (it){
                val action = PublicarServicioFragmentDirections.actionPublicarServicioFragmentToPublicacionFragment(id)
                v.findNavController().navigate(action)
                Snackbar.make(v, "Publicación modificada", Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(v,"ERROR",Snackbar.LENGTH_SHORT).show()
            }

        })


    }

    private fun verificarCamposVacios(): Boolean {
        var campoVacio = false
        var txvDefaultColor = 1979711488
        if (InputFieldValidator.esCampoVacio(binding.InTituloServicio, binding.txvInTituloServicioPS, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InDescripcionServicio, binding.txvInDescripcionServicioPS, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InPrecioHora, binding.txvInPrecioHoraPS, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio2(direccion, binding.txvInDireccionLaboralPS, txvDefaultColor) && !campoVacio) campoVacio = true
        return campoVacio
    }

}