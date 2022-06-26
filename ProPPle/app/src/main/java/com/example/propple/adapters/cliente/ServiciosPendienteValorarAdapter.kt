package com.example.propple.adapters.cliente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.fragments.cliente.homeFragmentDirections
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController

class ServiciosPendienteValorarAdapter(
    var ServiciosContratadosList: List<com.example.propple.api.Transacciones.Transaccion>
) : RecyclerView.Adapter<ServiciosPendienteValorarAdapter.ServiciosPendienteValorarHolder>() {

    //esto se conecta con el item
    class ServiciosPendienteValorarHolder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }

        fun setFecha(fecha: String){
            var txvFechaFinalizado : TextView = view.findViewById(R.id.txvFechaFinalizado)
            val fecha = fecha.replace("-"," / ").substring(0,14)
            txvFechaFinalizado.text = "Finalizado el " + fecha
        }

        fun setAvatar(img64:String){
            if (ProPPle.prefs.getUrlImageString()!="")
                imgController.getImgUrl(
                    img64,
                    view.context,
                    view.findViewById<ImageView>(R.id.fotoPrestador)
                )
        }

        fun setUbicacion(ubicacion : String){
            var txvUbicacion : TextView = view.findViewById(R.id.txvUbicacion)
            txvUbicacion.text = ubicacion
        }
        fun setTitulo(x: String, rubroAux: String){
            val txvServicioFinalizado : TextView = view.findViewById(R.id.txvServicioFinalizado)
            txvServicioFinalizado.setText(rubroAux+" - "+x)
        }

        fun valorar() {
            view.findViewById<Button>(R.id.btnValorar).setOnClickListener {
                view.findNavController().navigate(homeFragmentDirections.actionHomeFragmentToValorarServicioFragment())
            }
        }

    }

    // aca voy a buscar el xml de item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiciosPendienteValorarHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_servicios_pendientes_valorar,parent,false)
        return (ServiciosPendienteValorarHolder(view))
    }

    //esta funcion se ejecuta en cada iteracion de la lista
    override fun onBindViewHolder(holder: ServiciosPendienteValorarHolder, position: Int) {
        //ServiciosContratadosList[position].titulo?.let { holder.setRubro(it) }
        ServiciosContratadosList[position].location.let { holder.setUbicacion(it) }
        ServiciosContratadosList[position].url_download_image.let { holder.setAvatar(it) }
        ServiciosContratadosList[position].fecha.let {
            if (it != null) {
                holder.setFecha(it)
            }
        }
        val aliasAux = ServiciosContratadosList[position].alias
        val rubroAux = ServiciosContratadosList[position].rubro_name
        holder.setTitulo(aliasAux,rubroAux)
        ServiciosContratadosList[position].fecha.let {
        }
        holder.valorar()
    }


    //aca le pongo la cantidad de elemetos de la lista
    override fun getItemCount(): Int {
        return ServiciosContratadosList.size
    }

}