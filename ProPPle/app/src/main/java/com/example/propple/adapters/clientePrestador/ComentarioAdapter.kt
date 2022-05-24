package com.ort.casodeusotest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.ort.casodeusotest.entities.Comentario

class ComentarioAdapter (var comentarioList: MutableList<Comentario>,
                         var onClick : (Int) -> Unit) : RecyclerView.Adapter<ComentarioAdapter.ComentarioHolder>() {
    class ComentarioHolder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }
        fun getCard () : CardView {
            return view.findViewById(R.id.cardComentarioItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comentario,parent,false)
        return (ComentarioHolder(view))
    }

    override fun onBindViewHolder(holder: ComentarioHolder, position: Int) {
        holder.getCard().setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return comentarioList.size
    }

}