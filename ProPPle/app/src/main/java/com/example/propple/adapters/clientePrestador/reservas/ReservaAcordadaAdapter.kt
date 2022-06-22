package com.ort.casodeusotest.adapters.reservas

import android.content.Intent
import android.net.Uri
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
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.fragments.clientePrestador.ReservasFragmentDirections
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReservaAcordadaAdapter(
    var reservaList: List<Transaccion>/*,
                              var onClick : (Int) -> Unit*/) : RecyclerView.Adapter<ReservaAcordadaAdapter.ReservaHolder>() {

    class ReservaHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }

        /*fun getCard(): CardView {
            return view.findViewById(R.id.cardReservaAcordadaItem)
        }*/
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

        fun cancelar(trx: Transaccion){
            view.findViewById<FloatingActionButton>(R.id.fabCancelar).setOnClickListener {
                view.findNavController().navigate( ReservasFragmentDirections.actionReservasFragment2ToCancelarReservaFragment2(trx))
            }
        }
        fun wps(telefono: Int, mensaje: String, context: Any){
            val oldValue = " "
            val newValue = "%20"
            val output = mensaje.replace(oldValue, newValue)
            Log.i("mensaje", output)
            val url = "https://api.whatsapp.com/send?phone=${telefono}&text=${output}";
            val i = Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            view.findViewById<Button>(R.id.fabWhatsapp).setOnClickListener {
                view.context.startActivity(i)
            }
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva_acordada,parent,false)
        return (ReservaHolder(view))
    }

    override fun onBindViewHolder(holder: ReservaHolder, position: Int) {
        /*holder.getCard().setOnClickListener {
            onClick(position)
        }*/

        holder.cancelar(reservaList.get(position))
        //reservaList.get(position).telefono.let { holder.cancelar(it) }   //implemeta esto!
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
    }

    override fun getItemCount(): Int {
        return reservaList.size
    }
}