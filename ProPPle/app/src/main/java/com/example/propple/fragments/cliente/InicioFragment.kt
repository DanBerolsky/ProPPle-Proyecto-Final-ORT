package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.propple.viewModel.cliente.InicioViewModel
import com.example.propple.R

class inicioFragment : Fragment() {



    private lateinit var viewModel: InicioViewModel
    private lateinit var v : View
    private lateinit var btnIniciarSesion : Button
    private lateinit var btnRegistroUsuario : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.inicio_fragment, container, false)

        btnIniciarSesion = v.findViewById(R.id.btnIniciar_sesión)
        btnRegistroUsuario = v.findViewById(R.id.btnRegistrarse)
        return v
    }


    override fun onStart() {
        super.onStart()

        btnIniciarSesion.setOnClickListener {
            val action = inicioFragmentDirections.actionInicioFragmentToInicioSesionFragment()
            v.findNavController().navigate(action)
        }


        btnRegistroUsuario.setOnClickListener {
            val action = inicioFragmentDirections.actionInicioFragmentToRegistroUsuarioFragment()
            //val action = inicioFragmentDirections.actionInicioFragmentToRegistroDeCuentaActivity()
            v.findNavController().navigate(action)
        }


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InicioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}