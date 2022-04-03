package com.example.propple.adapters

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.entities.Publicacion
import kotlin.math.log

class PublicacionesAdapter(var publicacionesList : MutableList<Publicacion>) : RecyclerView.Adapter<PublicacionesAdapter.publicacionesHolder>(){



    //esto se conecta con el item
    class publicacionesHolder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }

        fun setRubro(rubro : String){
            var txtRubro : TextView = view.findViewById(R.id.txtRubro)
            txtRubro.text = rubro
        }

        fun setValoracion(cantEstrellas : Int){
            //val COLOR_AMARILLO : Color.argb =
            for ( i in 1..cantEstrellas){
                var estrellaAux = "estrella$i"
                Log.d("estre",estrellaAux)
                view.findViewWithTag<ImageView>(estrellaAux).setColorFilter(Color.argb(255, 235, 59, 1))
            }
        }

        fun setAvatar(){

        }

        fun setUbicacion(ubicacion : String){
            var txtUbicacion : TextView = view.findViewById(R.id.txtUbicacion)
            txtUbicacion.text = ubicacion
        }

    }

    // aca voy a buscar el xml de item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): publicacionesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.publicaciones_item_recycler_view,parent,false)
        return (publicacionesHolder(view))
    }

    //esta funcion se ejecuta en cada iteracion de la lista
    override fun onBindViewHolder(holder: publicacionesHolder, position: Int) {
        publicacionesList[position].titulo?.let { holder.setRubro(it) }
        publicacionesList[position].valoracion?.let { holder.setValoracion(it) }
        publicacionesList[position].ubicacion?.let { holder.setUbicacion(it) }
    }


    //aca le pongo la cantidad de elemetos de la lista
    override fun getItemCount(): Int {
        return publicacionesList.size
        Log.d("hola","hola")
    }


}