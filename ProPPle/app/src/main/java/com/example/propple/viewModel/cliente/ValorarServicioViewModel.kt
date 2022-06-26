package com.example.propple.viewModel.cliente

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.Puntuar
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.shared.ProPPle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ValorarServicioViewModel : ViewModel() {

    var status2 = MutableLiveData<Boolean>()

    fun valuarTransaccion(id_transaccion : Int,
                         comentario:String,
                         puntaje:Int ){
        val token= ProPPle.prefs.getJwt()
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Void> = RetrofitHelper.getRetrofit().create(
                Transacciones::class.java).valuarTransaccion(Puntuar(id_transaccion,
                comentario,
                puntaje,token))
            if(call.isSuccessful){
                status2.postValue(true)
            }else{
                status2.postValue(false)
            }
        }
    }
}