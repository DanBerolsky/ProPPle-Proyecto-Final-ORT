package com.ort.casodeusotest.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.propple.R
import com.ort.casodeusotest.viewModel.FormularioReservaViewModel

class FormularioReservaFragment : Fragment() {

    companion object {
        fun newInstance() = FormularioReservaFragment()
    }

    private lateinit var v : View
    private lateinit var viewModel: FormularioReservaViewModel
    private lateinit var btnEnviarReserva : Button
    private lateinit var fabVolverReservas1 : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.formulario_reserva_fragment, container, false)
        btnEnviarReserva = v.findViewById(R.id.btnEnviarReserva)
        fabVolverReservas1 = v.findViewById(R.id.fabVolverReservas1)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnEnviarReserva.setOnClickListener {
            val action = FormularioReservaFragmentDirections.actionFormularioReservaFragmentToReservasFragment2()
            v.findNavController().navigate(action)
        }
        fabVolverReservas1.setOnClickListener {
            val action = FormularioReservaFragmentDirections.actionFormularioReservaFragmentToReservasFragment2()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormularioReservaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}