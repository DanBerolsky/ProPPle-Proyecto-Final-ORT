package com.ort.casodeusotest.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.publication.Comentario
import com.example.propple.shared.ProPPle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ComentariosViewModel : ViewModel() {
    var comentarios= MutableLiveData<List<Comentario>?>()
    fun getComentarios(id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            var call : Response<List<Comentario>> = RetrofitHelper.getRetrofit().create(PublicationService::class.java).getPublicationCometarios(ProPPle.prefs.getJwt(),id)
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
}