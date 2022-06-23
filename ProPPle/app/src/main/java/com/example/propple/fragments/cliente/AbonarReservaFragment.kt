package com.example.propple.fragments.cliente

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController
import com.example.propple.viewModel.cliente.AbonarReservaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class AbonarReservaFragment : Fragment() {

    companion object {
        fun newInstance() = AbonarReservaFragment()
    }

    private lateinit var viewModel: AbonarReservaViewModel
    private lateinit var v:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.fragment_abonar_reserva, container, false)

        return v
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AbonarReservaViewModel::class.java)
        val trx=RechazarReservaFragmentArgs.fromBundle(requireArguments()).trx
        v.findViewById<Button>(R.id.btnAbonar).setOnClickListener {
            //Snackbar.make(v,trx.id_transaccion.toString(),Snackbar.LENGTH_SHORT).show()
            viewModel.rechazar(trx.id_transaccion)
        }

        setAvatar(trx.url_download_image)
        setTitulo(trx.alias,trx.rubro_name,v)


        viewModel.link.observe(viewLifecycleOwner, Observer {

            if (it!="" && it!=null){
                Snackbar.make(v,"Reserva abonada con éxito.", Snackbar.LENGTH_SHORT).show()
                //v.findNavController().navigate(AbonarReservaFragmentDirections.actionAbonarReservaFragmentToServiciosContratadosFragment2())
                if (it != null) {
                    getMercadoPAgo(it)
                }
            }else{
                Snackbar.make(v,"Error en el envio.", Snackbar.LENGTH_SHORT).show()
            }


        })

    }
    fun setAvatar(img64: String) {
        if (ProPPle.prefs.getUrlImageString() != "")
            imgController.getImgUrl(
                img64,
                v.context,
                v.findViewById<ImageView>(R.id.imageView17)
            )
    }
    fun setTitulo(x: String, rubroAux: String,view: View) {
        view.findViewById<TextView>(R.id.textView39).text= "$rubroAux - $x"
    }
    fun getMercadoPAgo(link:String){
        val i = Intent(Intent.ACTION_VIEW)
        i.setData(Uri.parse(link))
        startActivity(i)
    }

}