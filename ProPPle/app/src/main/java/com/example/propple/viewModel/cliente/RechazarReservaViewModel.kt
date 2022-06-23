package com.example.propple.viewModel.cliente

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.RechazarReserva
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.api.publication.Publication
import com.example.propple.shared.ProPPle.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RechazarReservaViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()

    fun rechazar(id:Int){

        CoroutineScope(Dispatchers.IO).launch {
            var call : Response<Void> = RetrofitHelper.getRetrofit().create(
                Transacciones::class.java).deleteTransaccionPresupuestada(RechazarReserva(prefs.getJwt(),id))
            if(call.isSuccessful){
                status.postValue(true)
            }else{
                status.postValue(false)
                Log.i("hola","errror")
            }


        }
    }


}