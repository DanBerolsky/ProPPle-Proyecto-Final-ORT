package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.viewModel.MisPreferenciasViewModel
import com.example.propple.R
import com.example.propple.databinding.MisPreferenciasFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs

class MisPreferenciasFragment : Fragment() {

    companion object {
        fun newInstance() = MisPreferenciasFragment()
    }

    private lateinit var viewModel: MisPreferenciasViewModel
    private lateinit var v:View
    private lateinit var binding:MisPreferenciasFragmentBinding
    private var direccion: String=prefs.getDireccion()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.mis_preferencias_fragment, container, false)
        binding=MisPreferenciasFragmentBinding.bind(v)
        return v
    }

    override fun onStart() {
        super.onStart()

        if (direccion!=""){
            binding.InDirecion.setText(direccion)
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MisPreferenciasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}