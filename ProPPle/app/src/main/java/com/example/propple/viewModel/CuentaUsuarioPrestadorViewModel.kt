package com.example.propple.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.UserClient.Sign
import com.example.propple.api.interfaces.UserClientService
import com.example.propple.shared.ProPPle.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CuentaUsuarioPrestadorViewModel : ViewModel() {
    val viewModelInicioSesionViewModel : InicioSesionViewModel = InicioSesionViewModel()
    val jwt = prefs.getJwt()
    val nombre = MutableLiveData<String>()
    val alias  = MutableLiveData<String>()

    fun getDatosFragment(){

        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Sign> = RetrofitHelper.getRetrofit().create(UserClientService::class.java).getOne(prefs.getJwt())
            if(call.isSuccessful){
                val response : Sign? = call.body()
                if (response!=null){
                    val nombreAux:String =response.user_name.toString()
                    val aliasAux:String= response.alias.toString()
                    nombre.postValue(nombreAux)
                    alias.postValue(aliasAux)
                    prefs.setNombre(nombreAux)
                    prefs.setAlias(aliasAux)
                    response.location?.let { prefs.setDireccion(it) }
                }

            }else{
                Log.i("hola","errror")
            }


        }
    }



}