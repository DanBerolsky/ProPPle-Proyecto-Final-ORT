package com.example.propple.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.RechazarReserva
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.api.publication.UpdatePublication
import com.example.propple.shared.ProPPle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DialogReservaHoyViewModel : ViewModel() {
    var status2 = MutableLiveData<Boolean>()
    var status = MutableLiveData<Boolean>()
    fun finalizarTransaccion(id:Int){
        val token= ProPPle.prefs.getJwt()
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Void> = RetrofitHelper.getRetrofit().create(
                Transacciones::class.java).
                finalizarTransaccion(RechazarReserva(token,id))
            if(call.isSuccessful){
                status2.postValue(true)
            }else{
                status2.postValue(false)
            }
        }
    }
    fun deleteTransaccionPresupuestada(id:Int){
        val token= ProPPle.prefs.getJwt()
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Void> = RetrofitHelper.getRetrofit().create(
                Transacciones::class.java).
            deleteTransaccionAbonada(RechazarReserva(token,id))
            if(call.isSuccessful){
                status.postValue(true)
            }else{
                status.postValue(false)
            }
        }
    }
}