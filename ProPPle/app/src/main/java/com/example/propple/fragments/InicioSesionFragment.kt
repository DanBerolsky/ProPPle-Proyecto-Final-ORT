package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.viewModel.InicioSesionViewModel
import com.example.propple.R

class InicioSesionFragment : Fragment() {

    companion object {
        fun newInstance() = InicioSesionFragment()
    }

    private lateinit var viewModel: InicioSesionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.inicio_sesion_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InicioSesionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}