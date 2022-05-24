package com.ort.casodeusotest.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.propple.R
import com.ort.casodeusotest.adapters.reservas.*
import com.ort.casodeusotest.entities.ReservaRepository
import com.ort.casodeusotest.viewModel.HistoricoReservasViewModel
import com.ort.casodeusotest.viewModel.ReservasViewModel

class HistoricoReservasFragment : Fragment() {

    companion object {
        fun newInstance() = HistoricoReservasFragment()
    }

    private lateinit var viewModel: HistoricoReservasViewModel
    private lateinit var v : View
    lateinit var recyclerReservasConcretadas : RecyclerView
    lateinit var recyclerReservasCanceladas : RecyclerView
    lateinit var recyclerReservasRechazadas : RecyclerView
    lateinit var recyclerReservasVencidas : RecyclerView
    lateinit var concretadaAdapter : ReservaConcretadaAdapter
    lateinit var canceladaAdapter : ReservaCanceladaAdapter
    lateinit var rechazadaAdapter : ReservaRechazadaAdapter
    lateinit var vencidaAdapter : ReservaVencidaAdapter
    var repository : ReservaRepository = ReservaRepository()
    private lateinit var fabVolverReservas2 : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.historico_reservas_fragment, container, false)
        fabVolverReservas2 = v.findViewById(R.id.fabVolverReservas2)
        recyclerReservasConcretadas = v.findViewById(R.id.recConcretadas)
        recyclerReservasCanceladas = v.findViewById(R.id.recCanceladas)
        recyclerReservasRechazadas = v.findViewById(R.id.recRechazadas)
        recyclerReservasVencidas = v.findViewById(R.id.recVencidas)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        fabVolverReservas2.setOnClickListener {
            val action = HistoricoReservasFragmentDirections.actionHistoricoReservasFragmentToReservasFragment2()
            v.findNavController().navigate(action)
        }

        recyclerReservasConcretadas.setHasFixedSize(true)
        recyclerReservasConcretadas.layoutManager = LinearLayoutManager(context)
        concretadaAdapter = ReservaConcretadaAdapter(repository.reservaList) {}
        recyclerReservasConcretadas.adapter = concretadaAdapter

        recyclerReservasCanceladas.setHasFixedSize(true)
        recyclerReservasCanceladas.layoutManager = LinearLayoutManager(context)
        canceladaAdapter = ReservaCanceladaAdapter(repository.reservaList) {}
        recyclerReservasCanceladas.adapter = canceladaAdapter

        recyclerReservasRechazadas.setHasFixedSize(true)
        recyclerReservasRechazadas.layoutManager = LinearLayoutManager(context)
        rechazadaAdapter = ReservaRechazadaAdapter(repository.reservaList) {}
        recyclerReservasRechazadas.adapter = rechazadaAdapter

        recyclerReservasVencidas.setHasFixedSize(true)
        recyclerReservasVencidas.layoutManager = LinearLayoutManager(context)
        vencidaAdapter = ReservaVencidaAdapter(repository.reservaList) {}
        recyclerReservasVencidas.adapter = vencidaAdapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoricoReservasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}