package com.example.propple.viewModel.cliente

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.CompraCli
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.api.publication.Publication
import com.example.propple.shared.ProPPle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ServiciosContratadosViewModel : ViewModel() {

    var listasDeCompras= MutableLiveData<CompraCli?>()

    fun getCompras() {
        CoroutineScope(Dispatchers.IO).launch {
            var call : Response<CompraCli> = RetrofitHelper.getRetrofit().create(
                Transacciones::class.java).getCompras(ProPPle.prefs.getJwt())
            if(call.isSuccessful){
                val response : CompraCli? = call.body()
                if (response!=null){
                    listasDeCompras.postValue(response)
                }else{
                    listasDeCompras.postValue(null)
                }
            }else{
                Log.i("hola","errror")
            }
        }
    }
}

