package com.example.propple.adapters.cliente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.api.publication.Comentario
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController

class Comentario2Adapter (var comentarioList: List<Comentario>) : RecyclerView.Adapter<Comentario2Adapter.Comentario2Holder>() {
    class Comentario2Holder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }
        /*fun getCard () : CardView {
            return view.findViewById(R.id.cardComentarioItem)
        }*/
        fun setAvatarPrestador(url:String){
            if (ProPPle.prefs.getUrlImageString()!="")
                imgController.getImgUrl(
                    url,
                    view.context,
                    view.findViewById<ImageView>(R.id.imgPrestador)
                )
        }
        fun setAvatarClietne(url:String){
            if (ProPPle.prefs.getUrlImageString()!="")
                imgController.getImgUrl(
                    url,
                    view.context,
                    view.findViewById<ImageView>(R.id.imgCliente)
                )
        }
        fun setPregunta(x:String){
            view.findViewById<TextView>(R.id.textPregunta).text=x
        }

        fun setPregunta2(x:String){
            view.findViewById<TextView>(R.id.textPregunta2).text=x
        }
        fun setRespuesta(x:String){
            view.findViewById<TextView>(R.id.textRespuesta).text=x
        }

        fun setRespuesta2(x:String){
            view.findViewById<TextView>(R.id.textRespuesta2).text=x
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Comentario2Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comentario2,parent,false)
        return (Comentario2Holder(view))
    }

    override fun onBindViewHolder(holder: Comentario2Holder, position: Int) {
        /*holder.getCard().setOnClickListener {
            onClick(position)
        }*/
        comentarioList.get(position).content.let { holder.setPregunta(it) }
        comentarioList.get(position).foto_cliente.let { holder.setAvatarClietne(it) }
        comentarioList.get(position).foto_prestador.let { holder.setAvatarPrestador(it) }
        comentarioList.get(position).date_of_creation.let { holder.setPregunta2("Enviado el  "+it.replace("-"," / ").substring(0,14)) }
        comentarioList.get(position).answer.let {
            if (it!="" && it!=null){
                holder.setRespuesta(it)
                holder.setRespuesta2("")
            }
             }
    }

    override fun getItemCount(): Int {
        return comentarioList.size
    }

}