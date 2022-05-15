package com.example.propple.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.UserClient.LoginModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.interfaces.UserClientService
import com.example.propple.api.UserClient.LoginModelRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class InicioSesionViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val status = MutableLiveData<String>()
    val rol = MutableLiveData<String>()


    fun login(mail:String, contrasenia:String){
        CoroutineScope(Dispatchers.IO).launch {
            var user : LoginModel = LoginModel(mail,contrasenia)
            val call : Response<LoginModelRes> = RetrofitHelper.getRetrofit().create(UserClientService::class.java).login(user)
            if(call.isSuccessful){
                Log.i("hola","isSuccessful")
                val response : LoginModelRes? = call.body()
                if (response!=null)
                    rol.postValue(response.rol)

            }else{
                //Log.i("hola","isFailed")
                Log.i("hola","Usuario incorecto")
                status.postValue("Usuario incorecto")
            }


        }
    }


}