package com.example.propple.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.viewModel.PublicacionesViewModel
import com.example.propple.R
import com.example.propple.adapters.PublicacionesAdapter
import com.example.propple.entities.publicacionesRepo

class PublicacionesFragment : Fragment() {



    companion object {
        fun newInstance() = PublicacionesFragment()
    }

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
        return v
    }


    override fun onStart() {
        super.onStart()
        recyclerPublicaciones.setHasFixedSize(true)
        recyclerPublicaciones.layoutManager = LinearLayoutManager(context)

        adapter = PublicacionesAdapter(repo.publicacionesList)
        recyclerPublicaciones.adapter=adapter // esta linea se renderiza la lista
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicacionesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}