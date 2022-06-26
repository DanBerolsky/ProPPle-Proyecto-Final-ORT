package com.example.propple.viewModel.cliente

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.shared.ProPPle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var status2 = MutableLiveData<Boolean>()
    var list = MutableLiveData<List<Transaccion>?>()
    fun getTransaccionesAValuar(){
        val token= ProPPle.prefs.getJwt()
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<List<Transaccion>> = RetrofitHelper.getRetrofit().create(
                Transacciones::class.java).getTransaccionesAValuar(token)
            if(call.isSuccessful){
                val body=call.body()
                if (body != null) {
                    if (body.size>0){
                        status2.postValue(true)
                        list.postValue(body)
                    }else{
                        status2.postValue(false)
                    }
                }else{
                    status2.postValue(false)
                }

            }else{
                status2.postValue(false)
            }
        }
    }
}