package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.viewModel.cliente.FormalizacionAcuerdoViewModel
import com.example.propple.viewModel.cliente.PublicacionVistaPublicaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FormalizacionAcuerdoFragment : Fragment() {

    companion object {
        fun newInstance() = FormalizacionAcuerdoFragment()
    }

    private lateinit var v : View
    private lateinit var btnSolicitar : Button
    private lateinit var viewModel: FormalizacionAcuerdoViewModel
    private var status : Boolean =false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_formalizacion_acuerdo, container, false)
        btnSolicitar = v.findViewById(R.id.btnSolicitar)
        return v
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormalizacionAcuerdoViewModel::class.java)
        var id = FormalizacionAcuerdoFragmentArgs.fromBundle(requireArguments()).id
        viewModel.crearTransaccion(id)
        viewModel.status.observe(viewLifecycleOwner, Observer {
            status=true
        })

        btnSolicitar.setOnClickListener {
            if (status){
                status=false
                Snackbar.make(v,"Listo",Snackbar.LENGTH_SHORT).show()
                val action = FormalizacionAcuerdoFragmentDirections.actionFormalizacionAcuerdoFragmentToFormalizacionEnvioFragment()
                v.findNavController().navigate(action)
            }else{
                Snackbar.make(v,"Error",Snackbar.LENGTH_SHORT).show()
            }


        }

    }

}