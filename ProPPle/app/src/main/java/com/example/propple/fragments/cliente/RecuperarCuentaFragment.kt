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
import com.example.propple.viewModel.cliente.RecuperarCuentaViewModel

class RecuperarCuentaFragment : Fragment() {

    companion object {
        fun newInstance() = RecuperarCuentaFragment()
    }

    private lateinit var v: View
    private lateinit var viewModel: RecuperarCuentaViewModel
    private lateinit var btnRecuperarCuenta : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.recuperar_cuenta_fragment, container, false)
        btnRecuperarCuenta = v.findViewById(R.id.btnRecuperar)
        return v
    }


    override fun onStart() {
        super.onStart()


        btnRecuperarCuenta.setOnClickListener {
            val action = RecuperarCuentaFragmentDirections.actionRecuperarCuentaFragmentToRecuperarCuenta2Fragment()
            v.findNavController().navigate(action)
        }


    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecuperarCuentaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}