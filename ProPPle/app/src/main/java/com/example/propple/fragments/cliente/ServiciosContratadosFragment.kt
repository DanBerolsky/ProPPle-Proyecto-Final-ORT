package com.example.propple.fragments.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.adapters.cliente.ServiciosContratadosPendienteDePagoAdapter
import com.example.propple.adapters.cliente.ServiciosContratadosProximosARealizarseAdapter
import com.example.propple.adapters.cliente.ServiciosIniciadosAdapter
import com.example.propple.entities.cliente.TransaccionesRepo
import com.example.propple.viewModel.cliente.ServiciosContratadosViewModel
import com.google.android.material.snackbar.Snackbar

class ServiciosContratadosFragment : Fragment() {

    companion object {
        fun newInstance() = ServiciosContratadosFragment()
    }

    private lateinit var viewModel: ServiciosContratadosViewModel
    lateinit var v:View
    lateinit var recyclerIniciado : RecyclerView
    lateinit var iniciadoAdapter : ServiciosIniciadosAdapter
    lateinit var recyclerPendienteDePago : RecyclerView
    lateinit var pendienteDePagoAdapter : ServiciosContratadosPendienteDePagoAdapter
    lateinit var recyclerProximoARealizarse : RecyclerView
    lateinit var proximosARealizarAdapter : ServiciosContratadosProximosARealizarseAdapter
    lateinit var btnHistServ : Button


    var repo : TransaccionesRepo = TransaccionesRepo()
    var repo2 : TransaccionesRepo = TransaccionesRepo()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.servicios_contratados_fragment, container, false)

        recyclerIniciado = v.findViewById(R.id.recIniciado)
        recyclerPendienteDePago = v.findViewById(R.id.recPendientePago)
        recyclerProximoARealizarse = v.findViewById(R.id.recProximoRealizar)
        btnHistServ = v.findViewById(R.id.btnHistServ)

        return v
    }

    override fun onStart() {
        super.onStart()
        recyclerIniciado.setHasFixedSize(true)
        recyclerIniciado.layoutManager = LinearLayoutManager(context)

        recyclerPendienteDePago.setHasFixedSize(true)
        recyclerPendienteDePago.layoutManager = LinearLayoutManager(context)

        recyclerProximoARealizarse.setHasFixedSize(true)
        recyclerProximoARealizarse.layoutManager = LinearLayoutManager(context)

        /*pendienteDePagoAdapter = ServiciosContratadosPendienteDePagoAdapter(repo.transaccionesList)
        recyclerPendienteDePago.adapter=pendienteDePagoAdapter // esta linea se renderiza la lista
        proximosARealizarAdapter = ServiciosContratadosProximosARealizarseAdapter(repo2.transaccionesList)
<<<<<<< HEAD
        recyclerProximoARealizarse.adapter=proximosARealizarAdapter // esta linea se renderiza la lista
=======
        recyclerProximoARealizarse.adapter=proximosARealizarAdapter // esta linea se renderiza la lista*/

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ServiciosContratadosViewModel::class.java)
        viewModel.getCompras()
        viewModel.listasDeCompras.observe(viewLifecycleOwner, Observer {
           if (it!=null){
               iniciadoAdapter = ServiciosIniciadosAdapter(it.proximos)
               recyclerIniciado.adapter=iniciadoAdapter // esta linea se renderiza la lista
               pendienteDePagoAdapter = ServiciosContratadosPendienteDePagoAdapter(it.pendientes)
               recyclerPendienteDePago.adapter=pendienteDePagoAdapter
               proximosARealizarAdapter = ServiciosContratadosProximosARealizarseAdapter(it.proximos)
               recyclerProximoARealizarse.adapter=proximosARealizarAdapter
           }else{
               Snackbar.make(v,"Error al cargar los datos.",Snackbar.LENGTH_SHORT).show()
           }
            btnHistServ.setOnClickListener {res->
                val action = it?.let { it1 ->
                    ServiciosContratadosFragmentDirections.actionServiciosContratadosFragmentToHistoricoServiciosFragment(
                        it1
                    )
                }
                if (action != null) {
                    v.findNavController().navigate(action)
                }
            }
            //dismissDialog(InicioSesionFragment.DIALOG_CARGANDO)
        } )
    }


}

