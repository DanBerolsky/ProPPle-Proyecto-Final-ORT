package com.example.propple.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.propple.viewModel.CuentaViewModel
import com.example.propple.R
import com.example.propple.databinding.CuentaFragmentBinding
import com.example.propple.databinding.CuentaUsuarioPrestadorFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs

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
    private lateinit var binding : CuentaFragmentBinding
    private  var nombre : String=""
    private  var alias : String=""
    private  var rol : String=prefs.getRol()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.cuenta_fragment, container, false)
        bntDatosPersonales = v.findViewById(R.id.bntDatosPersonales)
        bntCambiarContra = v.findViewById(R.id.bntCambiarContra)
        bntMisPreferencias = v.findViewById(R.id.bntMisPreferencias)
        bntPostularme = v.findViewById(R.id.bntPostularme)
        binding = CuentaFragmentBinding.bind(v)

        return v
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        viewModel.getDatosFragment()
        bntDatosPersonales.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToDatosPersonalesEditFragment()
            nav(action)
        }
        bntCambiarContra.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToCambiarContraseniaFragment()
            nav(action)
        }
        bntMisPreferencias.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToMisPreferenciasFragment()
            nav(action)
        }
        bntPostularme.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToPostularmeFragment()
            nav(action)
        }

    }

    private fun nav(action : NavDirections) {
        v.findNavController().navigate(action)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuentaViewModel::class.java)

        viewModel.nombre.observe(viewLifecycleOwner, Observer {result->
            nombre=result
            binding.NombreDeUsuario.setText(nombre)
        })


        viewModel.alias.observe(viewLifecycleOwner, Observer {result->
            alias=result
            binding.aliasRol.setText("$alias - $rol")
        })

    }

}