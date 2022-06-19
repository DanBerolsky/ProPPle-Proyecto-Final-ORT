package com.ort.casodeusotest.adapters.reservas

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ort.casodeusotest.entities.Reserva
import com.ort.casodeusotest.fragments.ReservasFragmentDirections

class ReservaAcordadaAdapter (var reservaList : MutableList<Reserva>/*,
                              var onClick : (Int) -> Unit*/) : RecyclerView.Adapter<ReservaAcordadaAdapter.ReservaHolder>() {

    class ReservaHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }

        /*fun getCard(): CardView {
            return view.findViewById(R.id.cardReservaAcordadaItem)
        }*/

        fun cancelar(id:Int){
            view.findViewById<FloatingActionButton>(R.id.fabCancelar).setOnClickListener {
                view.findNavController().navigate( ReservasFragmentDirections.actionReservasFragment2ToCancelarReservaFragment2(id))
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


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva_acordada,parent,false)
        return (ReservaHolder(view))
    }

    override fun onBindViewHolder(holder: ReservaHolder, position: Int) {
        /*holder.getCard().setOnClickListener {
            onClick(position)
        }*/

        holder.cancelar(1)
        //reservaList?.get(position)?.id?.let { holder.cancelar(it) }
        //reservaList?.get(position)?.telefono?.let { holder.cancelar(it) }   //implemeta esto!
    }

    override fun getItemCount(): Int {
        return reservaList.size
    }
}