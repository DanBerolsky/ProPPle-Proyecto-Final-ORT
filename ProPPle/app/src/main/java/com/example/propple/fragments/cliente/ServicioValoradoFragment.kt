package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.R
import com.example.propple.viewModel.cliente.ServicioValoradoViewModel

class ServicioValoradoFragment : Fragment() {

    companion object {
        fun newInstance() = ServicioValoradoFragment()
    }

    private lateinit var viewModel: ServicioValoradoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_servicio_valorado, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ServicioValoradoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}