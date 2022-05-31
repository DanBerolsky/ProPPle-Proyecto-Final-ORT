package com.example.propple.adapters.cliente

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.api.publication.Publication
import com.example.propple.fragments.cliente.PublicacionesFragmentDirections

class PublicacionesAdapter(var publicacionesList: List<Publication>?) : RecyclerView.Adapter<PublicacionesAdapter.publicacionesHolder>(){



    //esto se conecta con el item
    class publicacionesHolder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }

        fun setPrecio(precio: Int?){
            var txtPrecio : TextView = view.findViewById(R.id.txtPrecio)
            txtPrecio.text = "Precio base : $"+precio
        }

        fun setValoracion(cantEstrellas : Int){
            //val COLOR_AMARILLO : Color.argb =
            for ( i in 1..cantEstrellas){
                var estrellaAux = "estrella$i"
                Log.d("estre",estrellaAux)
                view.findViewWithTag<ImageView>(estrellaAux).setColorFilter(Color.argb(255, 245, 242, 66))
            }
        }

        fun setAvatar(){
        }

        fun setUbicacion(ubicacion : String){
            var txtUbicacion : TextView = view.findViewById(R.id.txtUbicacion)
            txtUbicacion.text = ubicacion
        }
        fun setTitulo(x:String){
            val txt:TextView=view.findViewById(R.id.txtTitle)
            txt.setText(x)
        }

        fun click() {
            view.findViewById<CardView>(R.id.acaDoc).setOnClickListener(){
                view.findNavController().navigate( PublicacionesFragmentDirections.actionPublicacionesFragmentToPublicacionVistaPublicaFragment())
            }
        }

    }

    // aca voy a buscar el xml de item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): publicacionesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.publicaciones_item_recycler_view,parent,false)
        return (publicacionesHolder(view))
    }

    //esta funcion se ejecuta en cada iteracion de la lista
    override fun onBindViewHolder(holder: publicacionesHolder, position: Int) {
        publicacionesList?.get(position)?.title.let {
            if (it != null) {
                holder.setTitulo(it)
            }
        }
        publicacionesList?.get(position)?.precio_x_hora.let { holder.setPrecio(it) }
        publicacionesList?.get(position)?.puntuacion?.let { holder.setValoracion(it) }
        publicacionesList?.get(position)?.location?.let { holder.setUbicacion(it) }
        publicacionesList?.get(position)?.id_publicacion?.let { holder.click() }
    }


    //aca le pongo la cantidad de elemetos de la lista
    override fun getItemCount(): Int {
        return publicacionesList?.size ?:
        Log.d("hola","hola")
    }


}