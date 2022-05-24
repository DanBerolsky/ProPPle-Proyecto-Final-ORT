package com.ort.casodeusotest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.ort.casodeusotest.entities.Rubro
import java.text.DateFormatSymbols

class RubroAdapter (var rubroList : MutableList<Rubro>,
                    var onClick : (Int) -> Unit) : RecyclerView.Adapter<RubroAdapter.RubroHolder>() {

    class RubroHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }

        fun setName(name: String) {
            var txtName: TextView = view.findViewById(R.id.txtRubroItemName)
            txtName.text = name
        }
        fun setSymbol(symbol : String) {
            var btnName: Button = view.findViewById(R.id.btnRubroItemName)
            btnName.text = symbol
        }

        fun getCard(): CardView {
            return view.findViewById(R.id.cardRubroItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RubroHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rubro,parent,false)
        return (RubroHolder(view))
    }

    override fun onBindViewHolder(holder: RubroHolder, position: Int) {
        holder.setName(rubroList[position].name)
        holder.setSymbol(rubroList[position].symbol)
        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return rubroList.size
    }
}