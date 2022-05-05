package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.propple.viewModel.CuentaViewModel
import com.example.propple.R

class CuentaFragment : Fragment() {

    companion object {
        fun newInstance() = CuentaFragment()
    }

    private lateinit var viewModel: CuentaViewModel
    private lateinit var v : View
    private lateinit var bntDatosPersonales : Button
    private lateinit var bntCambiarContra : Button
    private lateinit var bntMisPreferencias : Button
    private lateinit var bntPostularme : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.cuenta_fragment, container, false)
        bntDatosPersonales = v.findViewById(R.id.bntDatosPersonales)
        bntCambiarContra = v.findViewById(R.id.bntCambiarContra)
        bntMisPreferencias = v.findViewById(R.id.bntMisPreferencias)
        bntPostularme = v.findViewById(R.id.bntPostularme)
        return v
    }

    override fun onStart() {
        super.onStart()

        bntDatosPersonales.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToDatosPersonalesEditFragment()
            v.findNavController().navigate(action)
        }
        bntCambiarContra.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToCambiarContraseniaFragment()
            v.findNavController().navigate(action)
        }
        bntMisPreferencias.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToMisPreferenciasFragment()
            v.findNavController().navigate(action)
        }
        bntPostularme.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToPostularmeFragment()
            v.findNavController().navigate(action)
        }



    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuentaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}