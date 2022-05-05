package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.viewModel.CambiarContraseniaViewModel
import com.example.propple.R

class cambiarContraseniaFragment : Fragment() {

    companion object {
        fun newInstance() = cambiarContraseniaFragment()
    }

    private lateinit var viewModel: CambiarContraseniaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cambiar_contrasenia_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CambiarContraseniaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}