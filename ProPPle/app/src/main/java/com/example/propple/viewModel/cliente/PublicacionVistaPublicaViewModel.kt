package com.example.propple.viewModel.cliente

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.R
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.publication.Comentario
import com.example.propple.api.publication.ComentarioNuevo
import com.example.propple.api.publication.Publication
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PublicacionVistaPublicaViewModel : ViewModel() {
    var publi= MutableLiveData<Publication?>()
    var comentarios= MutableLiveData<List<Comentario>?>()
    var status= MutableLiveData<String?>()
    fun getPublication(id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            var call : Response<Publication> = RetrofitHelper.getRetrofit().create(
                PublicationService::class.java).getPublication(ProPPle.prefs.getJwt(),id)
            if(call.isSuccessful){
                val response : Publication? = call.body()
                if (response!=null){
                    publi.postValue(response)
                }

            }else{
                Log.i("hola","errror")
            }


        }
    }
    fun getComentarios(id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            var call : Response<List<Comentario>> = RetrofitHelper.getRetrofit().create(
                PublicationService::class.java).getPublicationCometarios(ProPPle.prefs.getJwt(),id)
            if(call.isSuccessful){
                val response : List<Comentario>? = call.body()
                if (response!=null){
                    comentarios.postValue(response)
                    Log.i("response",response.toString())
                }

            }else{
                Log.i("hola","errror")
            }


        }
    }

    fun newComment(id_publi: Int, content:String){
        val jwt =prefs.getJwt()
        if (jwt!=""&&content!=""){
            val comentarioAux=ComentarioNuevo(content,id_publi,jwt)
            CoroutineScope(Dispatchers.IO).launch {
                var call : Response<Void> = RetrofitHelper.getRetrofit().create(
                    PublicationService::class.java).nuevoComentario(comentarioAux)
                if(call.isSuccessful){
                    val response : Void? = call.body()
                        status.postValue("1")
                        Log.i("response","isSuccessful")


                }else{
                    status.postValue("2")
                    Log.i("hola","errror")
                }
            }
        }else{
            status.postValue("3")
        }


    }


}