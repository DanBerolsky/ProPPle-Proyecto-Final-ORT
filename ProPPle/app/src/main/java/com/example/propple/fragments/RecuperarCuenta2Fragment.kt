package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.R
import com.example.propple.viewModel.RecuperarCuenta2ViewModel

class RecuperarCuenta2Fragment : Fragment() {

    companion object {
        fun newInstance() = RecuperarCuenta2Fragment()
    }

    private lateinit var viewModel: RecuperarCuenta2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recuperar_cuenta2_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecuperarCuenta2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}