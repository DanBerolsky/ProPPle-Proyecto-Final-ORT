package com.ort.casodeusotest.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.publication.Comentario
import com.example.propple.api.publication.RespuestaComentario
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.utils.imgController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import retrofit2.Response

class ComentarioAdapter (var comentarioList: List<Comentario>) : RecyclerView.Adapter<ComentarioAdapter.ComentarioHolder>() {
    class ComentarioHolder (v : View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            this.view = v
        }
        val parentJob = Job()
        val scope = CoroutineScope(Dispatchers.Default + parentJob)




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
            view.findViewById<TextView>(R.id.textRespuesta).text=x
        }

        fun setPregunta2(x:String){
            view.findViewById<TextView>(R.id.textRespuesta2).text=x
        }
        fun setRespuesta(x:String){
            //view.findViewById<EditText>(R.id.inRes1).text=x
        }

        fun setRespuesta2(x:String){
            view.findViewById<TextView>(R.id.textRespuesta2).text=x
        }
        fun ocultarInput(id_comentario: Int, id_publicacion: Int){
            view.findViewById<FloatingActionButton>(R.id.btnEnviar).setOnClickListener {
                if (view.findViewById<EditText>(R.id.inRes1).text.trim()!="") {
                    val txtRes = view.findViewById<TextView>(R.id.textRespuesta3)
                    view.findViewById<CardView>(R.id.card).visibility = View.GONE
                    view.findViewById<FloatingActionButton>(R.id.btnEnviar).visibility = View.GONE
                    txtRes.visibility = View.VISIBLE
                    txtRes.text = view.findViewById<EditText>(R.id.inRes1).text
                    enviarComent(id_comentario, id_publicacion)
                }
            }
        }


        fun enviarComent(id_comentario: Int, id_publicacion: Int){
            /*var salida = false
            if(txtRes.text.trim()!=""){
                view.findViewById<FloatingActionButton>(R.id.btnEnviar).setOnClickListener {
                    if (view.findViewById<EditText>(R.id.inRes1).text.trim()!=""){*/
            val txtRes=view.findViewById<TextView>(R.id.textRespuesta3)
            val respuestaMensaje = RespuestaComentario(txtRes.text.toString(),id_comentario,id_publicacion,prefs.getJwt())
                        CoroutineScope(Dispatchers.IO).launch {
                            var call : Response<Void> = RetrofitHelper.getRetrofit().create(
                                PublicationService::class.java).postRespuesta(respuestaMensaje)
                            if(call.isSuccessful){
                                async{ Snackbar.make(view,"Enviado...",Snackbar.LENGTH_SHORT).show()}
                                //
                            }else {
                                async{Snackbar.make(view,"El mensaje no fue enviado.",Snackbar.LENGTH_SHORT).show()}
                            }
                        }
                    //}
                }
            }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comentario,parent,false)
        return (ComentarioHolder(view))
    }

    override fun onBindViewHolder(holder: ComentarioHolder, position: Int) {

        comentarioList[position].content.let { holder.setPregunta(it) }
        comentarioList[position].foto_cliente.let { holder.setAvatarClietne(it) }
        comentarioList[position].foto_prestador.let { holder.setAvatarPrestador(it) }
        comentarioList[position].date_of_creation.let { holder.setPregunta2("Enviado el  "+it.replace("-"," / ").substring(0,14)) }
        comentarioList.get(position).answer.let {
            if (it!="" && it!=null){
               // holder.setRespuesta(it)
                //holder.setRespuesta2("")
            }
            holder.ocultarInput(comentarioList[position].id_comentario, comentarioList[position].id_publicacion)

        }

    }

    override fun getItemCount(): Int {
        return comentarioList.size
    }

}

