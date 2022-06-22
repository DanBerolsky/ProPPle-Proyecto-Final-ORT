package com.example.propple.adapters.cliente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.entities.cliente.Transaccion

class ServiciosFinalizadosAdapter(
    var ServiciosContratadosList : MutableList<Transaccion>
) : RecyclerView.Adapter<ServiciosFinalizadosAdapter.ServiciosFinalizadosHolder>() {

    //esto se conecta con el item
    class ServiciosFinalizadosHolder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }

        fun setAvatar(){

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
        ServiciosContratadosList[position].id?.let {}
    }


    //aca le pongo la cantidad de elemetos de la lista
    override fun getItemCount(): Int {
        return ServiciosContratadosList.size
    }

}