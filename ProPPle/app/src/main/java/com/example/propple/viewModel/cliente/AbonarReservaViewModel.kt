package com.example.propple.viewModel.cliente

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.MercadoPagoRes
import com.example.propple.api.Transacciones.RechazarReserva
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.shared.ProPPle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class AbonarReservaViewModel : ViewModel() {
    val link = MutableLiveData<String>()

    fun rechazar(id:Int){

        CoroutineScope(Dispatchers.IO).launch {
            var call : Response<MercadoPagoRes> = RetrofitHelper.getRetrofit().create(
                Transacciones::class.java).abonarTransaccion(RechazarReserva(ProPPle.prefs.getJwt(),id))
            if(call.isSuccessful){
                val res:MercadoPagoRes? = call.body()
                if (res!=null){
                    link.postValue(res.init_point)
                }
            }else{
                link.postValue("")
                Log.i("hola","errror")
            }
        }
    }
}