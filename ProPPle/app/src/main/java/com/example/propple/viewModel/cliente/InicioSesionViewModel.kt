package com.example.propple.viewModel.cliente

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.UserClient.LoginModel
import com.example.propple.api.UserClient.LoginModelRes
import com.example.propple.api.UserClient.Sign
import com.example.propple.api.interfaces.LoginSignInService
import com.example.propple.api.interfaces.UserClientService
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class InicioSesionViewModel : ViewModel() {


    val status = MutableLiveData<String>()
    val rol = MutableLiveData<String>()
    var jwt : String = ""

    fun login(mail:String, contrasenia:String){
        CoroutineScope(Dispatchers.IO).launch {
            var user : LoginModel = LoginModel(mail,contrasenia)
            val call : Response<LoginModelRes> = RetrofitHelper.getRetrofit().create(LoginSignInService::class.java).login(user)
            if(call.isSuccessful){
                Log.i("hola","isSuccessful")
                val response : LoginModelRes? = call.body()
                if (response!=null){
                    val rolAux = response.rol
                    Log.i("hola",rolAux)
                    rol.postValue(rolAux)
                    jwt = response.jwt.token.toString()
                    prefs.setJwt(jwt)
                    prefs.setRol(rolAux)
                    getDatosFragment()
                }

            }else{
                //Log.i("hola","isFailed")
                Log.i("hola","Usuario incorecto")
                status.postValue("Usuario incorecto")
            }
            Log.i("hola",jwt)

        }
    }
    fun getDatosFragment(){

        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Sign> = RetrofitHelper.getRetrofit().create(UserClientService::class.java).getOne(prefs.getJwt())
            if(call.isSuccessful){
                val response : Sign? = call.body()
                if (response!=null){
                    val nombreAux:String =response.user_name.toString()
                    val aliasAux:String= response.alias.toString()
                    prefs.setNombre(nombreAux)
                    prefs.setAlias(aliasAux)
                    response.date_of_birth?.let { prefs.setFechaDeNacimiento(it) }
                    prefs.setphone(response.phone)
                    response.gender?.let { prefs.setGenero(it) }
                    response.location?.let { prefs.setDireccion(it) }
                    prefs.setApellido(response.user_last_name)
                    //response.url_image?.let { prefs.setUrlImageString(it) }
                    response.location_latitud?.let { prefs.setLat(it.toDouble()) }
                    response.location_longitud?.let { prefs.setLon(it.toDouble()) }

                }

            }else{
                Log.i("hola","errror")
            }


        }
    }


}