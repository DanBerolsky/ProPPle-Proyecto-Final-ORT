package com.example.propple.adapters.cliente

import android.graphics.Color
import android.util.Log
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


class ServiciosContratadosProximosARealizarseAdapter(var ServiciosContratadosList: List<com.example.propple.api.Transacciones.Transaccion>) : RecyclerView.Adapter<ServiciosContratadosProximosARealizarseAdapter.ServiciosContratadosHolder>(){



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



        fun setPrecio(precio: Double){
            var txtPrecio : TextView = view.findViewById(R.id.txtPrecio)
            txtPrecio.text = "Presupuesto : $"+precio
        }

        fun setValoracion(cantEstrellas : Int){
            //val COLOR_AMARILLO : Color.argb =
            for ( i in 1..cantEstrellas){
                var estrellaAux = "estrella$i"
                Log.d("estre",estrellaAux)
                view.findViewWithTag<ImageView>(estrellaAux).setColorFilter(Color.argb(255, 245, 242, 66))
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
        fun setTitulo(x:String){
            val txt:TextView=view.findViewById(R.id.txtTitle)
            txt.setText(x)
        }

    }

    // aca voy a buscar el xml de item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiciosContratadosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.servicios_contratados_proximo_a_realizar_item,parent,false)
        return (ServiciosContratadosHolder(view))
    }

    //esta funcion se ejecuta en cada iteracion de la lista
    override fun onBindViewHolder(holder: ServiciosContratadosHolder, position: Int) {
        //ServiciosContratadosList[position].titulo?.let { holder.setRubro(it) }
        //ServiciosContratadosList[position].valoracion?.let { holder.setValoracion(it) }
        ServiciosContratadosList[position].location.let { holder.setUbicacion(it) }
        ServiciosContratadosList[position].presupuesto.let { holder.setPrecio(it) }
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