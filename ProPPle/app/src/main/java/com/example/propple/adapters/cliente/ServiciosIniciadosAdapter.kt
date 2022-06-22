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

class ServiciosIniciadosAdapter(
    var ServiciosContratadosList: List<com.example.propple.api.Transacciones.Transaccion>
    ) : RecyclerView.Adapter<ServiciosIniciadosAdapter.ServiciosIniciadosHolder>() {

    //esto se conecta con el item
    class ServiciosIniciadosHolder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }
        fun setTitulo(x: String, rubroAux: String){
            val txt:TextView=view.findViewById(R.id.txtTitle)
            txt.setText(rubroAux+" - "+x)
        }

        fun setAvatar(img64:String){
            if (ProPPle.prefs.getUrlImageString()!="")
                imgController.getImgUrl(
                    img64,
                    view.context,
                    view.findViewById<ImageView>(R.id.btnAvatar)
                )
        }
    }

    // aca voy a buscar el xml de item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiciosIniciadosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_servicio_solicitado,parent,false)
        return (ServiciosIniciadosHolder(view))
    }

    //esta funcion se ejecuta en cada iteracion de la lista
    override fun onBindViewHolder(holder: ServiciosIniciadosHolder, position: Int) {
        //ServiciosContratadosList[position].titulo?.let { holder.setRubro(it) }
        //ServiciosContratadosList[position].id?.let {}
        ServiciosContratadosList[position].url_download_image.let { holder.setAvatar(it) }
        val aliasAux = ServiciosContratadosList[position].alias
        //val rubroAux = ServiciosContratadosList[position].rubro
        //holder.setTitulo(aliasAux,rubroAux)
    }


    //aca le pongo la cantidad de elemetos de la lista
    override fun getItemCount(): Int {
        return ServiciosContratadosList.size
    }

}