package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.viewModel.CuentaViewModel
import com.example.propple.R

class CuentaFragment : Fragment() {

    companion object {
        fun newInstance() = CuentaFragment()
    }

    private lateinit var viewModel: CuentaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cuenta_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuentaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}