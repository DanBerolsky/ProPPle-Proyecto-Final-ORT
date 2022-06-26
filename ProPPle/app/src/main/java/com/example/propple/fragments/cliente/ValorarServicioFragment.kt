package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.databinding.FragmentValorarServicioBinding
import com.example.propple.databinding.PublicacionFragmentBinding
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController
import com.example.propple.viewModel.cliente.ValorarServicioViewModel
import com.google.android.material.snackbar.Snackbar

class ValorarServicioFragment : Fragment() {

    companion object {
        fun newInstance() = ValorarServicioFragment()
    }

    private lateinit var viewModel: ValorarServicioViewModel
    private lateinit var v: View
    private lateinit var btnEnviarOpinion : Button
    private lateinit var binding: FragmentValorarServicioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_valorar_servicio, container, false)
        btnEnviarOpinion = v.findViewById(R.id.btnEnviarOpinion)
        binding=FragmentValorarServicioBinding.bind(v)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ValorarServicioViewModel::class.java)
        val trx = ValorarServicioFragmentArgs.fromBundle(requireArguments()).trx
        setAvatar(trx.url_download_image)
        trx.fecha?.let { setFecha(it) }
        setUbicacion(trx.location)
        var puntaje=0
        binding.floatingActionButton.setOnClickListener{ puntaje=1 }
        binding.floatingActionButton2.setOnClickListener{ puntaje=2 }
        binding.floatingActionButton3.setOnClickListener{ puntaje=3 }
        binding.floatingActionButton4.setOnClickListener{ puntaje=4 }
        binding.floatingActionButton5.setOnClickListener{ puntaje=5 }

        btnEnviarOpinion.setOnClickListener {
            var comentario=binding.InOpinion.text.toString()
            if (puntaje!=0 && comentario!=""){
                viewModel.valuarTransaccion(trx.id_transaccion, comentario, puntaje)
            }else{
                Snackbar.make(v,"formulario incompleto",Snackbar.LENGTH_SHORT).show()
            }

        }
        viewModel.status2.observe(viewLifecycleOwner, Observer {
            if (it){
                val action = ValorarServicioFragmentDirections.actionValorarServicioFragmentToServicioValoradoFragment2()
                v.findNavController().navigate(action)
            }else{
                Snackbar.make(v,"error",Snackbar.LENGTH_SHORT)
            }
        })

    }
    fun setFecha(fecha: String){
        var txvFechaFinalizado : TextView = v.findViewById(R.id.txvFecha)
        val fecha = fecha.replace("-"," / ").substring(0,14)
        txvFechaFinalizado.text = "Finalizado el " + fecha
    }

    fun setAvatar(img64:String){
        if (ProPPle.prefs.getUrlImageString()!="")
            imgController.getImgUrl(
                img64,
                v.context,
                v.findViewById<ImageView>(R.id.imgPrestador)
            )
    }

    fun setUbicacion(ubicacion : String){
        var txvUbicacion : TextView = v.findViewById(R.id.txvDireccion)
        txvUbicacion.text = ubicacion
    }
    fun setTitulo(x: String, rubroAux: String){
        val txvServicioFinalizado : TextView = v.findViewById(R.id.txvServicio)
        txvServicioFinalizado.setText(rubroAux+" - "+x)
    }



}