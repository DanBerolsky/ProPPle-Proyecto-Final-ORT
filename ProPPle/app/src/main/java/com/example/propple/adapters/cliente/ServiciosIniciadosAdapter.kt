package com.example.propple.adapters.cliente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.entities.cliente.Transaccion

class ServiciosIniciadosAdapter(
    var ServiciosContratadosList : MutableList<Transaccion>
    ) : RecyclerView.Adapter<ServiciosIniciadosAdapter.ServiciosIniciadosHolder>() {

    //esto se conecta con el item
    class ServiciosIniciadosHolder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }

        fun setRubro(rubro : String){
            var txtRubro : TextView = view.findViewById(R.id.txtNombre)
            txtRubro.text = rubro
        }

        fun setAvatar(){

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
        ServiciosContratadosList[position].id?.let {}
    }


    //aca le pongo la cantidad de elemetos de la lista
    override fun getItemCount(): Int {
        return ServiciosContratadosList.size
    }

}