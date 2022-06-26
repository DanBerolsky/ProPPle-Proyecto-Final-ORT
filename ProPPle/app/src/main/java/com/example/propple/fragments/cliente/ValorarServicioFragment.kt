package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.viewModel.cliente.ValorarServicioViewModel
import com.google.android.material.snackbar.Snackbar

class ValorarServicioFragment : Fragment() {

    companion object {
        fun newInstance() = ValorarServicioFragment()
    }

    private lateinit var viewModel: ValorarServicioViewModel
    private lateinit var v: View
    private lateinit var btnEnviarOpinion : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_valorar_servicio, container, false)
        btnEnviarOpinion = v.findViewById(R.id.btnEnviarOpinion)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnEnviarOpinion.setOnClickListener {
            val action = ValorarServicioFragmentDirections.actionValorarServicioFragmentToServicioValoradoFragment2()
            v.findNavController().navigate(action)
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ValorarServicioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}