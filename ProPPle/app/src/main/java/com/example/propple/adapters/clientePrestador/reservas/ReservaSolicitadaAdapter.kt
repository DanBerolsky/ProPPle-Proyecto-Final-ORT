package com.example.propple.adapters.clientePrestador.reservas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.fragments.cliente.PublicacionesFragmentDirections
import com.example.propple.fragments.clientePrestador.ReservasFragmentDirections
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.entities.Reserva


class ReservaSolicitadaAdapter(
    var reservaList: List<Transaccion>/*,
                                var onClick : (Int) -> Unit*/) : RecyclerView.Adapter<ReservaSolicitadaAdapter.ReservaHolder>() {

    class ReservaHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }

        /*fun getCard(): CardView {
            return view.findViewById(R.id.cardReservaDetalleItem)
        }*/
        fun setAvatar(img64:String){
            if (ProPPle.prefs.getUrlImageString()!="")
                imgController.getImgUrl(
                    img64,
                    view.context,
                    view.findViewById<ImageView>(R.id.btnAvatar)
                )
        }

        fun edit(id: Int) {
            view.findViewById<FloatingActionButton>(R.id.fabLlenarFormulario).setOnClickListener{
                view.findNavController().navigate( ReservasFragmentDirections.actionReservasFragment2ToFormularioReservaFragment2(id))
            }
        }
        fun cancelar(id: Int){
            view.findViewById<FloatingActionButton>(R.id.fabRechazarSolicitud).setOnClickListener{
                Snackbar.make(view,"Rechazar solicitud",Snackbar.LENGTH_SHORT).show()
            }
        }
        fun setTitulo(x: String, rubroAux: String){
            val txt: TextView =view.findViewById(R.id.txtTitle)
            txt.setText(rubroAux+" - "+x)
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva_solicitada,parent,false)
        return (ReservaHolder(view))
    }

    override fun onBindViewHolder(holder: ReservaHolder, position: Int) {
        /*holder.getCard().setOnClickListener {
            onClick(position)
        }*/

        reservaList[position].url_download_image.let { holder.setAvatar(it) }
        reservaList[position].id_transaccion.let { holder.edit(it) }
        reservaList[position].id_transaccion.let { holder.cancelar(it) }
    }

    override fun getItemCount(): Int {
        return reservaList.size
    }
}