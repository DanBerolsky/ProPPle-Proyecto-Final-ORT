package com.ort.casodeusotest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ort.casodeusotest.viewModel.PublicacionViewModel

class PublicacionFragment : Fragment() {

    companion object {
        fun newInstance() = PublicacionFragment()
    }

    private lateinit var v : View
    private lateinit var btnEditarPublicacion : FloatingActionButton
    private lateinit var btnComentarios : Button
    private lateinit var fabVolverMisPublic : FloatingActionButton

    private lateinit var viewModel: PublicacionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.publicacion_fragment, container, false)
        btnEditarPublicacion = v.findViewById(R.id.btnEditarPublicacion)
        btnComentarios = v.findViewById(R.id.btnComentarios)
        //fabVolverMisPublic = v.findViewById(R.id.fabVolverMisPublic)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnEditarPublicacion.setOnClickListener {
            val action = PublicacionFragmentDirections.actionPublicacionFragmentToPublicarServicioFragment(PublicacionFragmentArgs.fromBundle(requireArguments()).rubroPosition)
            v.findNavController().navigate(action)
        }
        btnComentarios.setOnClickListener {
            val action = PublicacionFragmentDirections.actionPublicacionFragmentToComentariosFragment(PublicacionFragmentArgs.fromBundle(requireArguments()).rubroPosition)
            v.findNavController().navigate(action)
        }
        /*fabVolverMisPublic.setOnClickListener {
            val action = PublicacionFragmentDirections.actionPublicacionFragmentToMisPublicacionesFragment()
            v.findNavController().navigate(action)
        }*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicacionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}