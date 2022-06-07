package com.example.propple.viewModel.cliente

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.UserClient.RecoverPassword
import com.example.propple.api.UserClient.Sign
import com.example.propple.api.interfaces.LoginSignInService
import com.example.propple.api.interfaces.UserClientService
import com.example.propple.shared.ProPPle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RecuperarCuentaViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun getDatosFragment(mail :String, v : View){
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Void> = RetrofitHelper.getRetrofit().create(LoginSignInService::class.java).recoverPassword(
                RecoverPassword(mail)
            )
            if(!call.isSuccessful) {
                Snackbar.make(v,"Error, por favor inténtelo de nuevo más tarde.",Snackbar.LENGTH_SHORT).show()
                //Log.i("hola", "errror")
            }
        }
    }
}