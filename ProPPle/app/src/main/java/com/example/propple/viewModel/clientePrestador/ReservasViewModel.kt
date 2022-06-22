package com.ort.casodeusotest.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.CompraCli
import com.example.propple.api.Transacciones.VentasPro
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.fragments.clientePrestador.HistoricoReservasFragment
import com.example.propple.shared.ProPPle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ReservasViewModel : ViewModel() {



    var listasDeReservas= MutableLiveData<VentasPro?>()

    fun getReservas() {
        CoroutineScope(Dispatchers.IO).launch {
            var call : Response<VentasPro> = RetrofitHelper.getRetrofit().create(
                Transacciones::class.java).getVentas(ProPPle.prefs.getJwt())
            if(call.isSuccessful){
                val response : VentasPro? = call.body()
                if (response!=null){
                    listasDeReservas.postValue(response)
                }else{
                    listasDeReservas.postValue(null)
                }
            }else{
                Log.i("hola","errror")
            }
        }
    }

}