package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController
import com.example.propple.viewModel.cliente.RechazarReservaViewModel
import com.google.android.material.snackbar.Snackbar

class RechazarReservaFragment : Fragment() {

    companion object {
        fun newInstance() = RechazarReservaFragment()
    }

    private lateinit var viewModel: RechazarReservaViewModel
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.fragment_rechazar_reserva, container, false)
        return v
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RechazarReservaViewModel::class.java)
        val trx=RechazarReservaFragmentArgs.fromBundle(requireArguments()).trx
        v.findViewById<Button>(R.id.btnRechazar).setOnClickListener {
            //Snackbar.make(v,trx.id_transaccion.toString(),Snackbar.LENGTH_SHORT).show()
            viewModel.rechazar(trx.id_transaccion)
        }

        setAvatar(trx.url_download_image)
        setTitulo(trx.alias,trx.rubro_name,v)

        viewModel.status.observe(viewLifecycleOwner, Observer {

            if (it){
                Snackbar.make(v,"Reserva rechazada con éxito.",Snackbar.LENGTH_SHORT).show()
                v.findNavController().navigate(RechazarReservaFragmentDirections.actionRechazarReservaFragment2ToServiciosContratadosFragment())
            }else{
                Snackbar.make(v,"Error en el envio.",Snackbar.LENGTH_SHORT).show()
            }


        })

    }
    fun setAvatar(img64: String) {
        if (ProPPle.prefs.getUrlImageString() != "")
            imgController.getImgUrl(
                img64,
                v.context,
                v.findViewById<ImageView>(R.id.btnAvatar)
            )
    }
    fun setTitulo(x: String, rubroAux: String,view: View) {
        view.findViewById<TextView>(R.id.txtTitle).text= "$rubroAux - $x"
    }

}