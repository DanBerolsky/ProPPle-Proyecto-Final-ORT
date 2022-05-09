package com.example.propple.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.adapters.ServiciosContratadosPendienteDePagoAdapter
import com.example.propple.adapters.ServiciosContratadosProximosARealizarseAdapter
import com.example.propple.entities.TransaccionesRepo
import com.example.propple.viewModel.ServiciosContratadosViewModel

class ServiciosContratadosFragment : Fragment() {

    companion object {
        fun newInstance() = ServiciosContratadosFragment()
    }

    private lateinit var viewModel: ServiciosContratadosViewModel
    lateinit var v:View
    lateinit var recyclerPendienteDePago : RecyclerView
    lateinit var pendienteDePagoAdapter : ServiciosContratadosPendienteDePagoAdapter
    lateinit var recyclerProximoARealizarse : RecyclerView
    lateinit var proximosARealizarAdapter : ServiciosContratadosProximosARealizarseAdapter

    var repo : TransaccionesRepo = TransaccionesRepo()
    var repo2 : TransaccionesRepo = TransaccionesRepo()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.servicios_contratados_fragment, container, false)

        recyclerPendienteDePago = v.findViewById(R.id.recPendientePago)
        recyclerProximoARealizarse = v.findViewById(R.id.recProximoRealizar)

        return v
    }

    override fun onStart() {
        super.onStart()
        recyclerPendienteDePago.setHasFixedSize(true)
        recyclerPendienteDePago.layoutManager = LinearLayoutManager(context)


        pendienteDePagoAdapter = ServiciosContratadosPendienteDePagoAdapter(repo.transaccionesList)
        recyclerPendienteDePago.adapter=pendienteDePagoAdapter // esta linea se renderiza la lista


        recyclerProximoARealizarse.setHasFixedSize(true)
        recyclerProximoARealizarse.layoutManager = LinearLayoutManager(context)


        proximosARealizarAdapter = ServiciosContratadosProximosARealizarseAdapter(repo2.transaccionesList)
        recyclerProximoARealizarse.adapter=proximosARealizarAdapter // esta linea se renderiza la lista
    }



}