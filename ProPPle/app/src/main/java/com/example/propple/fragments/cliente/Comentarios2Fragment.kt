package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.adapters.cliente.Comentario2Adapter
import com.example.propple.viewModel.cliente.Comentarios2ViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.entities.ComentarioRepository

class Comentarios2Fragment : Fragment() {

    companion object {
        fun newInstance() = Comentarios2Fragment()
    }

    private lateinit var viewModel: Comentarios2ViewModel
    private lateinit var v : View
    lateinit var recyclerComentarios2 : RecyclerView
    lateinit var adapter : Comentario2Adapter
    var repository : ComentarioRepository = ComentarioRepository()
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
        adapter = Comentario2Adapter(repository.comentarioList) {
            fabConsultar = v.findViewById(R.id.fabConsultar)
            fabConsultar.setOnClickListener{
                Snackbar.make(v, "Hiciste una consulta", Snackbar.LENGTH_SHORT).show()
            }
        }
        recyclerComentarios2.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Comentarios2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}