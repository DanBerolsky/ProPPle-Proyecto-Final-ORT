package com.example.propple.adapters.cliente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.entities.cliente.Transaccion
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController

class ServiciosFinalizadosAdapter(
    var ServiciosContratadosList: List<com.example.propple.api.Transacciones.Transaccion>
) : RecyclerView.Adapter<ServiciosFinalizadosAdapter.ServiciosFinalizadosHolder>() {

    //esto se conecta con el item
    class ServiciosFinalizadosHolder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }

        fun setFecha(fecha: String){
            var txtFecha : TextView = view.findViewById(R.id.txtFecha)
            val fecha = fecha.replace("-"," / ").substring(0,14)
            txtFecha.text = "Fecha : " + fecha
        }

        fun setPrecio(precio: Double){
            var txtPrecio : TextView = view.findViewById(R.id.txtPrecio)
            txtPrecio.text = "Presupuesto : $"+precio
        }
        fun setAvatar(img64:String){
            if (ProPPle.prefs.getUrlImageString()!="")
                imgController.getImgUrl(
                    img64,
                    view.context,
                    view.findViewById<ImageView>(R.id.btnAvatar)
                )
        }

        fun setUbicacion(ubicacion : String){
            var txtUbicacion : TextView = view.findViewById(R.id.txtUbicacion)
            txtUbicacion.text = ubicacion
        }
        fun setTitulo(x: String, rubroAux: String){
            val txt: TextView =view.findViewById(R.id.txtTitle)
            txt.setText(rubroAux+" - "+x)
        }

    }

    // aca voy a buscar el xml de item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiciosFinalizadosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_servicio_finalizado,parent,false)
        return (ServiciosFinalizadosHolder(view))
    }

    //esta funcion se ejecuta en cada iteracion de la lista
    override fun onBindViewHolder(holder: ServiciosFinalizadosHolder, position: Int) {
        //ServiciosContratadosList[position].titulo?.let { holder.setRubro(it) }
        ServiciosContratadosList[position].location.let { holder.setUbicacion(it) }
        ServiciosContratadosList[position].presupuesto.let {
            if (it != null) {
                holder.setPrecio(it)
            }
        }
        ServiciosContratadosList[position].url_download_image.let { holder.setAvatar(it) }
        val aliasAux = ServiciosContratadosList[position].alias
        ServiciosContratadosList[position].fecha.let {
            if (it != null) {
                holder.setFecha(it)
            }
        }
    }


    //aca le pongo la cantidad de elemetos de la lista
    override fun getItemCount(): Int {
        return ServiciosContratadosList.size
    }

}