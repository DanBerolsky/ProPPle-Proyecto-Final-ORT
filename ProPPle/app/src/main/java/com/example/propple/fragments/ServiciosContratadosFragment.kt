package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.R

class ServiciosContratadosFragment : Fragment() {

    companion object {
        fun newInstance() = ServiciosContratadosFragment()
    }

    private lateinit var viewModel: ServiciosContratadosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.servicios_contratados_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ServiciosContratadosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}