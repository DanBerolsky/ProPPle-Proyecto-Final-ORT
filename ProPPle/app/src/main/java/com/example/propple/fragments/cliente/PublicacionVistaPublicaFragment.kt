package com.example.propple.adapters.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.propple.R
import com.example.propple.viewModel.cliente.PublicacionVistaPublicaViewModel

class publicacionVistaPublicaFragment : Fragment() {

    companion object {
        fun newInstance() = publicacionVistaPublicaFragment()
    }

    private lateinit var viewModel: PublicacionVistaPublicaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_publicacion_vista_publica, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicacionVistaPublicaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}