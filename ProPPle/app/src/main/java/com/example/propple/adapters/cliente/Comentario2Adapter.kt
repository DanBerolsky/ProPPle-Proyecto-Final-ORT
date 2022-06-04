package com.example.propple.adapters.cliente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.ort.casodeusotest.entities.Comentario

class Comentario2Adapter (var comentarioList: MutableList<Comentario>,
                          var onClick : (Int) -> Unit) : RecyclerView.Adapter<Comentario2Adapter.Comentario2Holder>() {
    class Comentario2Holder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }
        fun getCard () : CardView {
            return view.findViewById(R.id.cardComentarioItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Comentario2Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comentario2,parent,false)
        return (Comentario2Holder(view))
    }

    override fun onBindViewHolder(holder: Comentario2Holder, position: Int) {
        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return comentarioList.size
    }

}