package com.example.propple.fragments.clientePrestador

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.adapters.reservas.ReservaAcordadaAdapter
import com.ort.casodeusotest.adapters.reservas.ReservaConfirmarAdapter
import com.example.propple.adapters.clientePrestador.reservas.ReservaSolicitadaAdapter
import com.example.propple.api.Transacciones.VentasPro
import com.ort.casodeusotest.entities.ReservaRepository
import com.ort.casodeusotest.viewModel.ReservasViewModel

class ReservasFragment : Fragment() {

    companion object {
        fun newInstance() = ReservasFragment()
    }

    private lateinit var viewModel: ReservasViewModel
    private lateinit var v : View
    lateinit var recyclerReservasAcordadas : RecyclerView
    lateinit var recyclerReservasConfirmar : RecyclerView
    lateinit var recyclerReservasSolicitadas : RecyclerView
    lateinit var acordadaAdapter : ReservaAcordadaAdapter
    lateinit var confirmarAdapter : ReservaConfirmarAdapter
    lateinit var solicitadaAdapter : ReservaSolicitadaAdapter
    var repository : ReservaRepository = ReservaRepository()
    private lateinit var btnHistoricoReservas : Button
    //private lateinit var fabWhatsapp : FloatingActionButton
    //private lateinit var fabCancelar : FloatingActionButton
    //private lateinit var fabLlenarFormulario : FloatingActionButton
    //private lateinit var fabRechazarSolicitud : FloatingActionButton
    private var lista: VentasPro? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.reservas_fragment, container, false)
        recyclerReservasAcordadas = v.findViewById(R.id.recAcordadas)
        recyclerReservasConfirmar = v.findViewById(R.id.recConfirmar)
        recyclerReservasSolicitadas = v.findViewById(R.id.recSolicitadas)
        btnHistoricoReservas = v.findViewById(R.id.btnHistoricoReservas)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        recyclerReservasAcordadas.setHasFixedSize(true)
        recyclerReservasAcordadas.layoutManager = LinearLayoutManager(context)
        /*acordadaAdapter = ReservaAcordadaAdapter(repository.reservaList) {
            fabWhatsapp = v.findViewById(R.id.fabWhatsapp)
            fabWhatsapp.setOnClickListener {}
            fabCancelar = v.findViewById(R.id.fabCancelar)
            fabCancelar.setOnClickListener {
                val action = ReservasFragmentDirections.actionReservasFragment2ToCancelarReservaFragment()
                v.findNavController().navigate(action)
            }
        }*/


        recyclerReservasConfirmar.setHasFixedSize(true)
        recyclerReservasConfirmar.layoutManager = LinearLayoutManager(context)

        recyclerReservasSolicitadas.setHasFixedSize(true)
        recyclerReservasSolicitadas.layoutManager = LinearLayoutManager(context)
        /*solicitadaAdapter = ReservaSolicitadaAdapter(repository.reservaList) {
            /*fabLlenarFormulario = v.findViewById(R.id.fabLlenarFormulario)
            fabLlenarFormulario.setOnClickListener {
                val action = ReservasFragmentDirections.actionReservasFragment2ToFormularioReservaFragment()
                v.findNavController().navigate(action)
            }*/
            fabRechazarSolicitud = v.findViewById(R.id.fabRechazarSolicitud)
            fabRechazarSolicitud.setOnClickListener {
                Snackbar.make(v, "Rechazaste la solicitud de reserva de Cliente", Snackbar.LENGTH_SHORT).show()
            }
        }*/


        btnHistoricoReservas.setOnClickListener {
            if (lista!=null){
                val action = ReservasFragmentDirections.actionReservasFragment2ToHistoricoReservasFragment2(
                    lista!!
                )
                v.findNavController().navigate(action)
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReservasViewModel::class.java)

        viewModel.getReservas()
        viewModel.listasDeReservas.observe(viewLifecycleOwner, Observer {result->
            if (result!=null){
                lista = result
                solicitadaAdapter = ReservaSolicitadaAdapter(result.inicial)
                recyclerReservasSolicitadas.adapter = solicitadaAdapter
                confirmarAdapter = ReservaConfirmarAdapter(result.pendientes)
                recyclerReservasConfirmar.adapter = confirmarAdapter
                acordadaAdapter= ReservaAcordadaAdapter(result.proximos)
                recyclerReservasAcordadas.adapter = acordadaAdapter
            }else{
                Snackbar.make(v,"Error al cargar los datos.",Snackbar.LENGTH_SHORT).show()
            }
            //dismissDialog(InicioSesionFragment.DIALOG_CARGANDO)
        } )
    }

}