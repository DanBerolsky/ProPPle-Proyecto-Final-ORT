package com.example.propple.fragments.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.viewModel.cliente.RecuperarCuenta2ViewModel

class RecuperarCuenta2Fragment : Fragment() {

    private lateinit var v : View
    private lateinit var btnRegrasarMenu : Button
    private lateinit var viewModel: RecuperarCuenta2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.recuperar_cuenta2_fragment, container, false)
        btnRegrasarMenu = v.findViewById(R.id.btnRegresarMenu1)
        return v
    }
    override fun onStart() {
        super.onStart()

        btnRegrasarMenu.setOnClickListener {
            val action = RecuperarCuenta2FragmentDirections.actionRecuperarCuenta2FragmentToInicioFragment()
            v.findNavController().navigate(action)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecuperarCuenta2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}