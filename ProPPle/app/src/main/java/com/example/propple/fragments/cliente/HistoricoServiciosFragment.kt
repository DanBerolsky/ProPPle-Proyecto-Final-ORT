package com.example.propple.fragments.cliente

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.adapters.cliente.*
import com.example.propple.entities.cliente.TransaccionesRepo
import com.example.propple.viewModel.cliente.HistoricoServiciosViewModel

class HistoricoServiciosFragment : Fragment() {

    companion object {
        fun newInstance() = HistoricoServiciosFragment()
    }

    private lateinit var viewModel: HistoricoServiciosViewModel
    lateinit var v:View
    lateinit var recyclerFinalizado : RecyclerView
    lateinit var finalizadoAdapter : ServiciosFinalizadosAdapter
    lateinit var recyclerCancelado : RecyclerView
    lateinit var canceladoAdapter : ServiciosCanceladosAdapter

    var repo : TransaccionesRepo = TransaccionesRepo()
    var repo2 : TransaccionesRepo = TransaccionesRepo()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_historico_servicios, container, false)
        recyclerFinalizado = v.findViewById(R.id.recFinalizado)
        recyclerCancelado = v.findViewById(R.id.recCancelado)
        return v
    }

    override fun onStart() {
        super.onStart()
        recyclerFinalizado.setHasFixedSize(true)
        recyclerFinalizado.layoutManager = LinearLayoutManager(context)


        finalizadoAdapter = ServiciosFinalizadosAdapter(repo.transaccionesList)
        recyclerFinalizado.adapter=finalizadoAdapter // esta linea se renderiza la lista


        recyclerCancelado.setHasFixedSize(true)
        recyclerCancelado.layoutManager = LinearLayoutManager(context)


        canceladoAdapter = ServiciosCanceladosAdapter(repo2.transaccionesList)
        recyclerCancelado.adapter=canceladoAdapter // esta linea se renderiza la lista

    }

}