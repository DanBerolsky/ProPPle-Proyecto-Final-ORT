package com.example.propple.fragments.cliente

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.adapters.cliente.*
import com.example.propple.entities.cliente.TransaccionesRepo
import com.example.propple.viewModel.cliente.HistoricoServiciosViewModel
import com.google.android.material.snackbar.Snackbar

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

        recyclerCancelado.setHasFixedSize(true)
        recyclerCancelado.layoutManager = LinearLayoutManager(context)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoricoServiciosViewModel::class.java)
        var compras = HistoricoServiciosFragmentArgs.fromBundle(requireArguments()).trxs
        if(compras!=null){
            canceladoAdapter = ServiciosCanceladosAdapter(compras.cancelados)
            recyclerCancelado.adapter=canceladoAdapter
            finalizadoAdapter = ServiciosFinalizadosAdapter(compras.finalizados)
            recyclerFinalizado.adapter=finalizadoAdapter
        }else{
            Snackbar.make(v,"Error no se pudieron cargar los datos", Snackbar.LENGTH_SHORT).show()
        }


    }

}