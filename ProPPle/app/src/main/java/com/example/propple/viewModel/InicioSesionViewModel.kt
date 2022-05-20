package com.example.propple.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.UserClient.LoginModel
import com.example.propple.api.UserClient.LoginModelRes
import com.example.propple.api.interfaces.LoginSignInService
import com.example.propple.shared.ProPPle.Companion.prefs
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
                }

            }else{
                //Log.i("hola","isFailed")
                Log.i("hola","Usuario incorecto")
                status.postValue("Usuario incorecto")
            }
            Log.i("hola",jwt)

        }
    }


}