package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.R
import com.example.propple.viewModel.ValidacionDeCuentaViewModel

class validacionDeCuentaFragment : Fragment() {

    companion object {
        fun newInstance() = validacionDeCuentaFragment()
    }

    private lateinit var viewModel: ValidacionDeCuentaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.validacion_de_cuenta_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ValidacionDeCuentaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}