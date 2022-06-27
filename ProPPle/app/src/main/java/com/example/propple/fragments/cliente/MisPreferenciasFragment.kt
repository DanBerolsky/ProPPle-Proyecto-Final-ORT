package com.example.propple.fragments.cliente

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.propple.R
import com.example.propple.databinding.MisPreferenciasFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.utils.GoogleMaps
import com.example.propple.viewModel.cliente.MisPreferenciasViewModel
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.material.snackbar.Snackbar


class MisPreferenciasFragment : Fragment() {

    companion object {
        fun newInstance() = MisPreferenciasFragment()
    }

    private lateinit var viewModel: MisPreferenciasViewModel
    private lateinit var v:View
    private lateinit var binding:MisPreferenciasFragmentBinding
    private var direccion: String=""
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val PERMISSION_REQUEST_ACCESS_LOCATION=100
    private var googleMaps: GoogleMaps = GoogleMaps()
    private lateinit var autocompleteSupportFragment1:AutocompleteSupportFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.mis_preferencias_fragment, container, false)
        binding=MisPreferenciasFragmentBinding.bind(v)
        direccion=prefs.getDireccion()
        binding.switch1.setChecked(prefs.getMejorValoracion())
        binding.switch12.setChecked(prefs.getMenorPrecioBase())
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

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
        autocompleteSupportFragment1 = (getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment1) as AutocompleteSupportFragment?)!!

        // Information that we wish to fetch after typing
        // the location and clicking on one of the options
        autocompleteSupportFragment1!!.setPlaceFields(
            listOf(
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.PHONE_NUMBER,
                Place.Field.LAT_LNG,
                Place.Field.OPENING_HOURS,
                Place.Field.RATING,
                Place.Field.USER_RATINGS_TOTAL))

        // Display the fetched information after clicking on one of the options
        autocompleteSupportFragment1.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {

                // Text view where we will
                // append the information that we fetch
                val textView = binding.tv1

                // Information about the place
                val name = place.name
                val address = place.address
                //val phone = place.phoneNumber.toString()
                val latlng = place.latLng
                val latitude = latlng?.latitude
                val longitude = latlng?.longitude

                if (latitude != null) {
                    prefs.setLat(latitude)
                }
                if (longitude != null) {
                    prefs.setLon(longitude)
                }


                val isOpenStatus : String = if(place.isOpen == true){
                    "Open"
                } else {
                    "Closed"
                }

                val rating = place.rating
                val userRatings = place.userRatingsTotal

                /*Name: $name \nAddress: $address \nPhone Number: $phone \n" +
                "Latitude, Longitude: $latitude , $longitude \nIs open: $isOpenStatus \n" +
                        "Rating: $rating \nUser ratings: $userRatings
                */
                textView.text = "${address}"
            }

            override fun onError(status: Status) {
                Toast.makeText(applicationContext,"Some error occurred", Toast.LENGTH_SHORT).show()
            }
        })

        return v
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== PERMISSION_REQUEST_ACCESS_LOCATION){
            if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(requireContext(),"Granted", Toast.LENGTH_SHORT).show()
                googleMaps.getCurrentLocation(requireActivity(),requireContext(),fusedLocationClient)
            }else{
                Toast.makeText(requireContext(),"Denied", Toast.LENGTH_SHORT).show()

            }
        }
    }
    override fun onStart() {
        super.onStart()
        binding.btnUbicacion.setOnClickListener { googleMaps.getCurrentLocation(requireActivity(),requireContext(),fusedLocationClient)
            googleMaps.dir.observe(viewLifecycleOwner, Observer {
                prefs.setDireccion(it)
                //binding.InDirecion.setText(it)
                autocompleteSupportFragment1.setText(it)
            }) }
        if (direccion!=""){
            autocompleteSupportFragment1.setText(direccion)
        }


        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
           if (isChecked){
               Snackbar.make(v,"Activado",Snackbar.LENGTH_SHORT).show()
           }else{
               Snackbar.make(v,"Desactivado",Snackbar.LENGTH_SHORT).show()
           }
        }
        binding.switch12.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                Snackbar.make(v,"Activado",Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(v,"Desactivado",Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.btnGuardar2.setOnClickListener {
            prefs.setDireccion(direccion)
            val v1=binding.switch1.isChecked
            val v2=binding.switch12.isChecked
            prefs.setMejorValoracion(v1)
            prefs.setMenorPrecioBase(v2)
            Snackbar.make(v,"Listo!",Snackbar.LENGTH_SHORT).show()

        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MisPreferenciasViewModel::class.java)
    }

}