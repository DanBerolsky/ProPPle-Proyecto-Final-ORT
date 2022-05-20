package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.CuentaUsuarioPrestadorFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.viewModel.CuentaUsuarioPrestadorViewModel
import com.google.android.material.snackbar.Snackbar

class CuentaUsuarioPrestadorFragment : Fragment() {

    companion object {
        fun newInstance() = CuentaUsuarioPrestadorFragment()
    }

    private lateinit var viewModel: CuentaUsuarioPrestadorViewModel
    private lateinit var v : View
    private lateinit var binding : CuentaUsuarioPrestadorFragmentBinding
    private lateinit var nombre : String
    private lateinit var alias : String
    private val rol : String = prefs.getRol()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.cuenta_usuario_prestador_fragment, container, false)
        binding = CuentaUsuarioPrestadorFragmentBinding.bind(v)
        return v
    }

    override fun onStart() {
        super.onStart()
        viewModel.getDatosFragment()
        binding.bntDatosPersonales.setOnClickListener { nav(CuentaUsuarioPrestadorFragmentDirections.actionCuentaUsuarioPrestadorFragmentToDatosPersonalesEditFragment2()) }
        binding.bntCambiarContra.setOnClickListener { nav(CuentaUsuarioPrestadorFragmentDirections.actionCuentaUsuarioPrestadorFragmentToCambiarContraseniaFragment2()) }
        binding.bntMisPreferencias.setOnClickListener { nav(CuentaUsuarioPrestadorFragmentDirections.actionCuentaUsuarioPrestadorFragmentToMisPreferenciasFragment2()) }
        binding.bntPostularme.setOnClickListener { nav(CuentaUsuarioPrestadorFragmentDirections.actionCuentaUsuarioPrestadorFragmentToPostularmeFragment2()) }
        //binding.bntMisPublicaciones.setOnClickListener { nav(CuentaUsuarioPrestadorFragmentDirections.) }

    }

    private fun nav(action : NavDirections) {
        v.findNavController().navigate(action)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuentaUsuarioPrestadorViewModel::class.java)



        viewModel.nombre.observe(viewLifecycleOwner, Observer {result->
            nombre=result
            binding.NombreDeUsuario.setText(nombre)

        })


        viewModel.alias.observe(viewLifecycleOwner, Observer {result->
            alias=result
            binding.aliasRol.setText(alias +" - " + rol)
        })

    }

}