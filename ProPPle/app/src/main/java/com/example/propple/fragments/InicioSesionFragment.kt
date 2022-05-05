package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.propple.viewModel.InicioSesionViewModel
import com.example.propple.R

class InicioSesionFragment : Fragment() {

   private lateinit var v: View

    private lateinit var viewModel: InicioSesionViewModel
    private lateinit var btnOlvideClave : Button
    private lateinit var btnIniciar_sesión : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.inicio_sesion_fragment, container, false)
        btnOlvideClave = v.findViewById(R.id.btnOlvide)
        btnIniciar_sesión =   v.findViewById(R.id.btnIniciar_sesión)
        return v
    }


    override fun onStart() {
        super.onStart()


        btnOlvideClave.setOnClickListener {
            val action = InicioSesionFragmentDirections.actionInicioSesionFragmentToRecuperarCuentaFragment()
            v.findNavController().navigate(action)
        }

        btnIniciar_sesión.setOnClickListener {
            val action = InicioSesionFragmentDirections.actionInicioSesionFragmentToMainActivity2()
            v.findNavController().navigate(action)
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InicioSesionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}