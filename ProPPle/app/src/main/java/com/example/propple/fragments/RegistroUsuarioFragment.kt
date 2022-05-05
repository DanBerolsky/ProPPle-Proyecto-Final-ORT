package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.viewModel.RegistroUsuarioViewModel

class RegistroUsuarioFragment : Fragment() {

    private lateinit var v : View
    private lateinit var btnRegistrar : Button


    private lateinit var viewModel: RegistroUsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.registro_usuario_fragment, container, false)
        btnRegistrar = v.findViewById(R.id.btnRegistrarUsuario)

        return v
    }


    override fun onStart() {
        super.onStart()

        btnRegistrar.setOnClickListener {
            val action = RegistroUsuarioFragmentDirections.actionRegistroUsuarioFragmentToValidacionDeCuentaFragment()
            v.findNavController().navigate(action)
        }

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistroUsuarioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}