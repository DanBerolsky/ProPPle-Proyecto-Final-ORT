package com.ort.casodeusotest.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.CrearTransaccion
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.api.publication.Publication
import com.example.propple.api.publication.PublicationCuenta
import com.example.propple.shared.ProPPle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MisPublicacionesViewModel : ViewModel() {


    var status = MutableLiveData<Boolean>()
    var lista = MutableLiveData<List<PublicationCuenta>>()
    fun getPublicationsForPrestador(){
        val jwt = ProPPle.prefs.getJwt()
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<List<PublicationCuenta>> = RetrofitHelper.getRetrofit().create(PublicationService::class.java).
                getPublicationsForPrestador(jwt)
            if(call.isSuccessful){
                status.postValue(true)
                lista.postValue(call.body())
            }else{
                Log.i("hola","errror")
                status.postValue(false)
            }
        }
    }

}