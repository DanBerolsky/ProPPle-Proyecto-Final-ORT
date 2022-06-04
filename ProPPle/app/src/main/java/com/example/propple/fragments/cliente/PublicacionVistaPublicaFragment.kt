package com.example.propple.adapters.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.fragments.cliente.FormalizacionAcuerdoFragment
import com.example.propple.viewModel.cliente.PublicacionVistaPublicaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ort.casodeusotest.fragments.PublicacionFragmentArgs
import com.ort.casodeusotest.fragments.PublicacionFragmentDirections

class publicacionVistaPublicaFragment : Fragment() {

    companion object {
        fun newInstance() = publicacionVistaPublicaFragment()
    }

    private lateinit var v : View
    private lateinit var fabContactar : FloatingActionButton
    private lateinit var btnComen2 : Button
    private lateinit var viewModel: PublicacionVistaPublicaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_publicacion_vista_publica, container, false)
        fabContactar = v.findViewById(R.id.fabContactar)
        btnComen2 = v.findViewById(R.id.btnComen2)
        return v

    }

    override fun onStart() {
        super.onStart()
        fabContactar.setOnClickListener {
            val action = publicacionVistaPublicaFragmentDirections.actionPublicacionVistaPublicaFragmentToFormalizacionAcuerdoFragment2()
            v.findNavController().navigate(action)
        }
        btnComen2.setOnClickListener {
            val action = publicacionVistaPublicaFragmentDirections.actionPublicacionVistaPublicaFragmentToComentarios2Fragment2()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicacionVistaPublicaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}