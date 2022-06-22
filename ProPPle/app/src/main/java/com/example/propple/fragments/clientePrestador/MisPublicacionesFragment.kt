package com.ort.casodeusotest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.ort.casodeusotest.adapters.RubroAdapter
import com.ort.casodeusotest.entities.RubroRepository
import com.ort.casodeusotest.viewModel.MisPublicacionesViewModel

class MisPublicacionesFragment : Fragment() {

    lateinit var v : View

    companion object {
        fun newInstance() = MisPublicacionesFragment()
    }

    private lateinit var viewModel: MisPublicacionesViewModel
    lateinit var recyclerRubro : RecyclerView
    lateinit var adapter : RubroAdapter
    var repository : RubroRepository = RubroRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.mis_publicaciones_fragment, container, false)
        recyclerRubro = v.findViewById(R.id.recRubro)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        recyclerRubro.setHasFixedSize(true)
        recyclerRubro.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MisPublicacionesViewModel::class.java)
        viewModel.getPublicationsForPrestador()
        viewModel.lista.observe(viewLifecycleOwner, Observer {
            adapter = RubroAdapter(it)
            recyclerRubro.adapter = adapter
        })


    }

}