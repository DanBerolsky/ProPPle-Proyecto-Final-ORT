package com.example.propple.viewModel.cliente

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.publication.CrearPostulacion
import com.example.propple.shared.ProPPle.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PostularmeViewModel : ViewModel() {



    val status = MutableLiveData<String>()

    fun postPostulacion(rubro: String,img:String) {
        val crearPostulacion:CrearPostulacion= CrearPostulacion(data = img, rubro_name = rubro,prefs.getJwt())
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<Void> =
                RetrofitHelper.getRetrofit().create(PublicationService::class.java)
                    .postPostulacion(crearPostulacion)
            if (call.isSuccessful) {
                status.postValue("Enviado!")
                //enviar mail?
                Log.i("hola","exito")
            } else {
                status.postValue("error")
                Log.i("hola","error")
            }


        }
    }


}