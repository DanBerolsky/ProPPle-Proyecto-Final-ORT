package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.viewModel.cliente.ValidacionDeCuentaViewModel

class validacionDeCuentaFragment : Fragment() {

    private lateinit var v : View
    private lateinit var btnRegrasarMenu : Button
    private lateinit var viewModel: ValidacionDeCuentaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.validacion_de_cuenta_fragment, container, false)
        btnRegrasarMenu = v.findViewById(R.id.btnRegresarMenu1)
        return v
    }



    override fun onStart() {
        super.onStart()

        btnRegrasarMenu.setOnClickListener {
            val action = validacionDeCuentaFragmentDirections.actionValidacionDeCuentaFragmentToInicioFragment()
            v.findNavController().navigate(action)
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ValidacionDeCuentaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}