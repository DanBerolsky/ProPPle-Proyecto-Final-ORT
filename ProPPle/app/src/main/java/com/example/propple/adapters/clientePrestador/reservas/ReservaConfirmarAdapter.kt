package com.ort.casodeusotest.adapters.reservas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController
import com.ort.casodeusotest.entities.Reserva

class ReservaConfirmarAdapter(
    var reservaList: List<Transaccion>): RecyclerView.Adapter<ReservaConfirmarAdapter.ReservaHolder>() {

    class ReservaHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }

        fun getCard(): CardView {
            return view.findViewById(R.id.cardReservaDetalleItem)
        }
        fun setAvatar(img64:String){
            if (ProPPle.prefs.getUrlImageString()!="")
                imgController.getImgUrl(
                    img64,
                    view.context,
                    view.findViewById<ImageView>(R.id.btnAvatar)
                )
        }
        fun setTitulo(x: String, rubroAux: String){
            val txt: TextView =view.findViewById(R.id.txtTitle)
            txt.setText(rubroAux+" - "+x)
        }
        fun setUbicacion(ubicacion : String){
            var txtUbicacion : TextView = view.findViewById(R.id.txtUbicacion)
            txtUbicacion.text = ubicacion
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

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva_detalle,parent,false)
        return (ReservaHolder(view))
    }

    override fun onBindViewHolder(holder: ReservaHolder, position: Int) {
        holder.getCard().setOnClickListener {
            //onClick(position)
        }
        reservaList[position].url_download_image.let { holder.setAvatar(it) }
        reservaList[position].location.let { holder.setUbicacion(it) }
        reservaList[position].presupuesto.let {
            if (it != null) {
                holder.setPrecio(it)
            }
        }
        reservaList[position].fecha.let {
            if (it != null) {
                holder.setFecha(it)
            }
        }
        val aliasAux = reservaList[position].alias
        val rubroAux = reservaList[position].rubro_name
        holder.setTitulo(aliasAux,rubroAux)
    }

    override fun getItemCount(): Int {
        return reservaList.size
    }
}