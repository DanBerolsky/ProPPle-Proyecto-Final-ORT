package com.example.propple.adapters.cliente

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.entities.cliente.Transaccion
import com.example.propple.fragments.cliente.ServiciosContratadosFragmentDirections
import com.google.android.material.snackbar.Snackbar

class ServiciosContratadosPendienteDePagoAdapter(var ServiciosContratadosList : MutableList<Transaccion>) : RecyclerView.Adapter<ServiciosContratadosPendienteDePagoAdapter.ServiciosContratadosHolder>(){



    //esto se conecta con el item
    class ServiciosContratadosHolder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }

        fun setRubro(rubro : String){
            var txtRubro : TextView = view.findViewById(R.id.txtNombre)
            txtRubro.text = rubro
        }

        fun setValoracion(cantEstrellas : Int){
            //val COLOR_AMARILLO : Color.argb =
            for ( i in 1..cantEstrellas){
                var estrellaAux = "estrella$i"
                Log.d("estre",estrellaAux)
                view.findViewWithTag<ImageView>(estrellaAux).setColorFilter(Color.argb(255, 245, 188, 66))
            }
        }

        fun setAvatar(){

        }

        fun setUbicacion(ubicacion : String){
            var txtUbicacion : TextView = view.findViewById(R.id.txtUbicacion)
            txtUbicacion.text = ubicacion
        }

        fun abonar(id:Int){
            view.findViewById<Button>(R.id.btnAbonar).setOnClickListener {
                view.findNavController().navigate(ServiciosContratadosFragmentDirections.actionServiciosContratadosFragment2ActionServiciosContratadosFragmentToAbonarReservaFragment(id))
            }
        }
        fun Rechazar(){
            view.findViewById<Button>(R.id.btnRechazar).setOnClickListener {
                Snackbar.make(view,"ELIMINAR",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    // aca voy a buscar el xml de item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiciosContratadosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.servicios_contratados_pendiente_pago_item,parent,false)
        return (ServiciosContratadosHolder(view))
    }

    //esta funcion se ejecuta en cada iteracion de la lista
    override fun onBindViewHolder(holder: ServiciosContratadosHolder, position: Int) {
        //ServiciosContratadosList[position].titulo?.let { holder.setRubro(it) }
        //ServiciosContratadosList[position].valoracion?.let { holder.setValoracion(it) }
        //ServiciosContratadosList[position].ubicacion?.let { holder.setUbicacion(it) }
        ServiciosContratadosList[position].id?.let { holder.abonar(it as Int) }
        holder.Rechazar()
    }


    //aca le pongo la cantidad de elemetos de la lista
    override fun getItemCount(): Int {
        return ServiciosContratadosList.size
    }


}