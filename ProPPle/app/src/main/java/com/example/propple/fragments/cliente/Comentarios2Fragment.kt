package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.adapters.cliente.Comentario2Adapter
import com.example.propple.adapters.cliente.publicacionVistaPublicaFragmentArgs
import com.example.propple.viewModel.cliente.Comentarios2ViewModel
import com.example.propple.viewModel.cliente.PublicacionVistaPublicaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.adapters.ComentarioAdapter


class Comentarios2Fragment : Fragment() {

    companion object {
        fun newInstance() = Comentarios2Fragment()
    }

    private lateinit var viewModel: PublicacionVistaPublicaViewModel
    private lateinit var v : View
    lateinit var recyclerComentarios2 : RecyclerView
    lateinit var adapter : Comentario2Adapter
    //var repository : ComentarioRepository = ComentarioRepository()
    private lateinit var fabConsultar : FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_comentarios2, container, false)
        recyclerComentarios2 = v.findViewById(R.id.recComentarios2)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        recyclerComentarios2.setHasFixedSize(true)
        recyclerComentarios2.layoutManager = LinearLayoutManager(context)
        /*adapter = Comentario2Adapter(repository.comentarioList) {
            fabConsultar = v.findViewById(R.id.fabConsultar)
            fabConsultar.setOnClickListener{
                Snackbar.make(v, "Hiciste una consulta", Snackbar.LENGTH_SHORT).show()
            }
        }
        recyclerComentarios2.adapter = adapter*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicacionVistaPublicaViewModel::class.java)
        var servicios = Comentarios2FragmentArgs.fromBundle(requireArguments()).id
        Log.i("id",servicios.toString())
        viewModel.getComentarios(servicios.toInt())
        viewModel.comentarios.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                adapter = Comentario2Adapter(it)
                recyclerComentarios2.adapter=adapter
            }

        })
    }

}