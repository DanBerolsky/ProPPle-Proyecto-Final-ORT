package com.ort.casodeusotest.adapters.reservas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.ort.casodeusotest.entities.Reserva

class ReservaVencidaAdapter (var reservaList : MutableList<Reserva>,
                             var onClick : (Int) -> Unit) : RecyclerView.Adapter<ReservaVencidaAdapter.ReservaHolder>() {

    class ReservaHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }

        fun getCard(): CardView {
            return view.findViewById(R.id.cardReservaRechazadaItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva_rechazada,parent,false)
        return (ReservaHolder(view))
    }

    override fun onBindViewHolder(holder: ReservaHolder, position: Int) {
        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return reservaList.size
    }
}