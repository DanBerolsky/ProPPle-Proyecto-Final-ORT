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
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController
import com.google.android.material.snackbar.Snackbar

class ServiciosContratadosPendienteDePagoAdapter(var ServiciosContratadosList: List<com.example.propple.api.Transacciones.Transaccion>) : RecyclerView.Adapter<ServiciosContratadosPendienteDePagoAdapter.ServiciosContratadosHolder>(){



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

        fun abonar(id:Int){
            view.findViewById<Button>(R.id.btnAbonar).setOnClickListener {
                view.findNavController().navigate(ServiciosContratadosFragmentDirections.actionServiciosContratadosFragmentToAbonarReservaFragment2(id))
            }
        }
        fun Rechazar(){
            view.findViewById<Button>(R.id.btnRechazar).setOnClickListener {
                Snackbar.make(view,"ELIMINAR",Snackbar.LENGTH_SHORT).show()
            }
        }
        fun setFecha(fecha: String){
            var txtFecha : TextView = view.findViewById(R.id.txtFecha)
            val fecha = fecha.replace("-"," / ").substring(0,14)
            txtFecha.text = "Fecha : " + fecha
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
        ServiciosContratadosList[position].location.let { holder.setUbicacion(it) }
        ServiciosContratadosList[position].url_download_image.let { holder.setAvatar(it) }
        ServiciosContratadosList[position].id_transaccion.let { holder.abonar(it) }
        ServiciosContratadosList[position].fecha.let {
            if (it != null) {
                holder.setFecha(it)
            }
        }
        holder.Rechazar()
    }


    //aca le pongo la cantidad de elemetos de la lista
    override fun getItemCount(): Int {
        return ServiciosContratadosList.size
    }


}