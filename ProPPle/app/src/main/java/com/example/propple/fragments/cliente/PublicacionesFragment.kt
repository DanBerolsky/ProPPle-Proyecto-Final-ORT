package com.example.propple.fragments.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.adapters.cliente.PublicacionesAdapter
import com.example.propple.entities.cliente.publicacionesRepo
import com.example.propple.fragments.DialogCargarFragment
import com.example.propple.viewModel.cliente.PublicacionesViewModel

class PublicacionesFragment : Fragment() {


    lateinit var v:View
    private lateinit var viewModel: PublicacionesViewModel
    lateinit var recyclerPublicaciones : RecyclerView
    lateinit var adapter : PublicacionesAdapter
    var repo : publicacionesRepo = publicacionesRepo()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v =inflater.inflate(R.layout.publicaciones_fragment, container, false)
        recyclerPublicaciones = v.findViewById(R.id.recPublicaciones)
        DialogCargarFragment().show(childFragmentManager, InicioSesionFragment.DIALOG_CARGANDO)
        return v
    }


    override fun onStart() {
        super.onStart()
        recyclerPublicaciones.setHasFixedSize(true)
        recyclerPublicaciones.layoutManager = LinearLayoutManager(context)
    }

    fun dismissDialog(tag:String){
        val prev: Fragment? = getChildFragmentManager().findFragmentByTag(tag)
        if (prev != null) {
            val df : DialogFragment = prev as DialogFragment
            df.dismiss()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicacionesViewModel::class.java)
        var servicios = PublicacionesFragmentArgs.fromBundle(requireArguments()).rubroIndex
        viewModel.getPublications(servicios)
        viewModel.publiXRubro.observe(viewLifecycleOwner, Observer {
            adapter = PublicacionesAdapter(it)
            recyclerPublicaciones.adapter=adapter
            dismissDialog(InicioSesionFragment.DIALOG_CARGANDO)
        } )
    }




}