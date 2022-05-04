package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.viewModel.MisPreferenciasViewModel
import com.example.propple.R

class MisPreferenciasFragment : Fragment() {

    companion object {
        fun newInstance() = MisPreferenciasFragment()
    }

    private lateinit var viewModel: MisPreferenciasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mis_preferencias_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MisPreferenciasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}