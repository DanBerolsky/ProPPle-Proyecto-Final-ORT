package com.ort.casodeusotest.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.FormalizarTransaccion
import com.example.propple.api.Transacciones.VentasPro
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class FormularioReservaViewModel : ViewModel() {
    var status= MutableLiveData<Boolean>()

    fun enviarFormulario(date_of_work: String,
                         id_transaccion: Int,
                         location: String,
                         location_latitud: Double,
                         location_longitud: Double,
                         presupuesto: Int) {
        val formulario = FormalizarTransaccion(date_of_work,
        id_transaccion,
        location,
        location_latitud,
        location_longitud,
        presupuesto,
        prefs.getJwt())
        CoroutineScope(Dispatchers.IO).launch {
            var call : Response<Void> = RetrofitHelper.getRetrofit().create(
                Transacciones::class.java).formalizarTransaccion(formulario)
            if(call.isSuccessful){
                val response : Void? = call.body()
                    status.postValue(true)
            }else{
                status.postValue(false)
            }
        }
    }
}