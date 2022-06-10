package com.example.propple.viewModel.cliente

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.CrearTransaccion
import com.example.propple.api.UserClient.Sign
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.api.interfaces.UserClientService
import com.example.propple.shared.Prefs
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class FormalizacionAcuerdoViewModel : ViewModel() {
    var status = MutableLiveData<Boolean>()
    fun crearTransaccion(id:Int){
        val jwt = prefs.getJwt()
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Void> = RetrofitHelper.getRetrofit().create(Transacciones::class.java).crearTransaccion(CrearTransaccion(id,jwt))
            if(call.isSuccessful){
                status.postValue(true)
            }else{
                Log.i("hola","errror")
                status.postValue(false)
            }
        }
    }
    private fun salida(z:Boolean):Boolean{
        return z
    }
}