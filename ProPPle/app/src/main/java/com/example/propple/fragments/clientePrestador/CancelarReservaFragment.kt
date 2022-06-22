package com.example.propple.fragments.clientePrestador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.CancelarReservaFragmentBinding
import com.example.propple.databinding.FragmentComentarios2Binding
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.viewModel.CancelarReservaViewModel

class CancelarReservaFragment : Fragment() {

    companion object {
        fun newInstance() = CancelarReservaFragment()
    }

    private lateinit var v : View
    private lateinit var viewModel: CancelarReservaViewModel
    private lateinit var inMotivo : EditText
    private lateinit var btnNoRegresar : Button
    private lateinit var btnSiCancelar : Button
    private lateinit var binding: CancelarReservaFragmentBinding
    //private lateinit var fabVolverReservas3 : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.cancelar_reserva_fragment, container, false)
        btnNoRegresar = v.findViewById(R.id.btnNoRegresar)
        btnSiCancelar = v.findViewById(R.id.btnSiCancelar)
        //fabVolverReservas3 = v.findViewById(R.id.fabVolverReservas3)
        inMotivo = v.findViewById(R.id.InMotivo)
        binding= CancelarReservaFragmentBinding.bind(v)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnNoRegresar.setOnClickListener { v.findNavController().navigate(CancelarReservaFragmentDirections.actionCancelarReservaFragmentToReservasFragment22())}
        btnSiCancelar.setOnClickListener {
            if(inMotivo.text.isBlank()) {
                Snackbar.make(v, "Debes ingresar un motivo", Snackbar.LENGTH_SHORT).show()
            } else {
            }
        }
        /*fabVolverReservas3.setOnClickListener {
            val action = CancelarReservaFragmentDirections.actionCancelarReservaFragmentToReservasFragment22()
            v.findNavController().navigate(action)
        }*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val trx = CancelarReservaFragmentArgs.fromBundle(requireArguments()).trx
        viewModel = ViewModelProvider(this).get(CancelarReservaViewModel::class.java)
        setAvatar(trx.url_download_image)
        trx.fecha?.let { setFecha(it) }
        setUbicacion(trx.location)
        trx.presupuesto?.let { setPrecio(it) }
    }
    fun setFecha(fecha: String){
        var txtFecha : TextView = v.findViewById(R.id.txtFecha)
        val fecha = fecha.replace("-"," / ").substring(0,14)
        txtFecha.text = "Fecha : " + fecha
    }
    fun setUbicacion(ubicacion : String){
        var txtUbicacion : TextView = v.findViewById(R.id.txtUbicacion)
        txtUbicacion.text = ubicacion
    }
    fun setPrecio(precio: Double){
        var txtPrecio : TextView = v.findViewById(R.id.txtPrecio)
        txtPrecio.text = "Presupuesto : $"+precio
    }

    fun setAvatar(img64:String){
        if (ProPPle.prefs.getUrlImageString()!="")
            imgController.getImgUrl(
                img64,
                v.context,
                v.findViewById<ImageView>(R.id.btnAvatar)
            )
    }


}