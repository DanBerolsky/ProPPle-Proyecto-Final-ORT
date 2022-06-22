package com.example.propple.fragments.clientePrestador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.fragments.cliente.publicacionVistaPublicaFragmentArgs
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.adapters.reservas.ReservaCanceladaAdapter
import com.ort.casodeusotest.adapters.reservas.ReservaConcretadaAdapter
import com.ort.casodeusotest.viewModel.ReservasViewModel

class HistoricoReservasFragment : Fragment() {

    companion object {
        fun newInstance() = HistoricoReservasFragment()
    }

    private lateinit var viewModel: ReservasViewModel
    private lateinit var v : View
    private lateinit var recyclerReservasConcretadas : RecyclerView
    private lateinit var recyclerReservasCanceladas : RecyclerView
    //private lateinit var recyclerReservasRechazadas : RecyclerView
    //lateinit var recyclerReservasVencidas : RecyclerView
    private lateinit var concretadaAdapter : ReservaConcretadaAdapter
    private lateinit var canceladaAdapter : ReservaCanceladaAdapter
    //lateinit var rechazadaAdapter : ReservaRechazadaAdapter
    //lateinit var vencidaAdapter : ReservaVencidaAdapter
    //var repository : ReservaRepository = ReservaRepository()
    //private lateinit var fabVolverReservas2 : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.historico_reservas_fragment, container, false)
        //fabVolverReservas2 = v.findViewById(R.id.fabVolverReservas2)
        recyclerReservasConcretadas = v.findViewById(R.id.recConcretadas)
        recyclerReservasCanceladas = v.findViewById(R.id.recCanceladas)
        //recyclerReservasRechazadas = v.findViewById(R.id.recRechazadas)
        //recyclerReservasVencidas = v.findViewById(R.id.recVencidas)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        /*fabVolverReservas2.setOnClickListener {
            val action = HistoricoReservasFragmentDirections.actionHistoricoReservasFragmentToReservasFragment2()
            v.findNavController().navigate(action)
        }*/

        recyclerReservasConcretadas.setHasFixedSize(true)
        recyclerReservasConcretadas.layoutManager = LinearLayoutManager(context)


        recyclerReservasCanceladas.setHasFixedSize(true)
        recyclerReservasCanceladas.layoutManager = LinearLayoutManager(context)

        /*recyclerReservasRechazadas.setHasFixedSize(true)
        recyclerReservasRechazadas.layoutManager = LinearLayoutManager(context)
        rechazadaAdapter = ReservaRechazadaAdapter(repository.reservaList) {}
        recyclerReservasRechazadas.adapter = rechazadaAdapter

        recyclerReservasVencidas.setHasFixedSize(true)
        recyclerReservasVencidas.layoutManager = LinearLayoutManager(context)
        vencidaAdapter = ReservaVencidaAdapter(repository.reservaList) {}
        recyclerReservasVencidas.adapter = vencidaAdapter]*/

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReservasViewModel::class.java)
        var ventas = HistoricoReservasFragmentArgs.fromBundle(requireArguments()).venta
        if(ventas!=null){
            canceladaAdapter = ReservaCanceladaAdapter(ventas.cancelados)
            recyclerReservasCanceladas.adapter = canceladaAdapter
            concretadaAdapter = ReservaConcretadaAdapter(ventas.finalizados)
            recyclerReservasConcretadas.adapter = concretadaAdapter
        }else{
            Snackbar.make(v,"Error no se pudieron cargar los datos",Snackbar.LENGTH_SHORT).show()
        }


    }

}