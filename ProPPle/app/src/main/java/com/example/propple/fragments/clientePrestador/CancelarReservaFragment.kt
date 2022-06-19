package com.example.propple.fragments.clientePrestador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.viewModel.CancelarReservaViewModel

class CancelarReservaFragment : Fragment() {

    companion object {
        fun newInstance() = CancelarReservaFragment()
    }

    private lateinit var v : View
    private lateinit var viewModel: CancelarReservaViewModel
    private lateinit var inMotivo : EditText
    private lateinit var btnNoRegresar : Button
    private lateinit var btnSiCancelar : Button
    //private lateinit var fabVolverReservas3 : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.cancelar_reserva_fragment, container, false)
        btnNoRegresar = v.findViewById(R.id.btnNoRegresar)
        btnSiCancelar = v.findViewById(R.id.btnSiCancelar)
        //fabVolverReservas3 = v.findViewById(R.id.fabVolverReservas3)
        inMotivo = v.findViewById(R.id.inMotivo)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnNoRegresar.setOnClickListener { v.findNavController().navigate(CancelarReservaFragmentDirections.actionCancelarReservaFragmentToReservasFragment22())}
        btnSiCancelar.setOnClickListener {
            if(inMotivo.text.isBlank()) {
                Snackbar.make(v, "Debes ingresar un motivo", Snackbar.LENGTH_SHORT).show()
            } else {
            }
        }
        /*fabVolverReservas3.setOnClickListener {
            val action = CancelarReservaFragmentDirections.actionCancelarReservaFragmentToReservasFragment22()
            v.findNavController().navigate(action)
        }*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CancelarReservaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}