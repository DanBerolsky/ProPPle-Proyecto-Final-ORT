package com.example.propple.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.UserClient.LoginModelRes
import com.example.propple.api.UserClient.Sign
import com.example.propple.api.interfaces.UserClientService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class RegistroUsuarioViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val status = MutableLiveData<String>()

    fun sign(user : Sign) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<Void> =
                RetrofitHelper.getRetrofit().create(UserClientService::class.java).sign(user)
            if (call.isSuccessful) {
                status.postValue("ok")
                Log.i("hola","exito")
            } else {
                status.postValue("error")
                Log.i("hola","error")
            }


        }
    }



}

