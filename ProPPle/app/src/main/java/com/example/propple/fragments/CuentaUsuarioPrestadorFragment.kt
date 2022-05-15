package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.R
import com.example.propple.viewModel.CuentaUsuarioPrestadorViewModel

class CuentaUsuarioPrestadorFragment : Fragment() {

    companion object {
        fun newInstance() = CuentaUsuarioPrestadorFragment()
    }

    private lateinit var viewModel: CuentaUsuarioPrestadorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cuenta_usuario_prestador_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuentaUsuarioPrestadorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}