package com.example.propple.fragments.cliente

import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.propple.viewModel.cliente.MisPreferenciasViewModel
import com.example.propple.R
import com.example.propple.databinding.MisPreferenciasFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.utils.GoogleMaps
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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
                binding.InDirecion.setText(it)
            }) }
        if (direccion!=""){
            binding.InDirecion.setText(direccion)
        }

        binding.btnGuardar2.setOnClickListener {
            prefs.setDireccion(binding.InDirecion.text.toString())
            prefs.setMejorValoracion(binding.switch1.isChecked)
            prefs.setMenorPrecioBase(binding.switch12.isChecked)
            Snackbar.make(v,"Listo!",Snackbar.LENGTH_SHORT).show()
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MisPreferenciasViewModel::class.java)
    }

}