package com.example.propple.viewModel

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.UserClient.PasswordChange
import com.example.propple.api.UserClient.Sign
import com.example.propple.api.interfaces.UserClientService
import com.example.propple.fragments.cambiarContraseniaFragmentDirections
import com.example.propple.fragments.inicioFragmentDirections
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CambiarContraseniaViewModel : ViewModel() {

    var cerrar = MutableLiveData<Boolean>()


    fun passwordChange(curPass:String,pass1:String,pass2:String,v: View) {
        if (pass1 != pass2){
            Snackbar.make(v,"Las contraseñas ingresadas no coinciden", Snackbar.LENGTH_SHORT).show()
            return
        }else if(pass1==curPass){
            Snackbar.make(v,"Las contraseñas son todas iguales", Snackbar.LENGTH_SHORT).show()
            return
        }

        val passwordChange: PasswordChange = PasswordChange(pass1, prefs.getJwt(), curPass)

        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<Void> =
                RetrofitHelper.getRetrofit().create(UserClientService::class.java)
                    .passwordChange(passwordChange)
            if (call.isSuccessful) {
                Snackbar.make(v, "La contraseña ha sido cambiada", Snackbar.LENGTH_SHORT).show()
                cerrar.postValue(true)
            } else {
                Snackbar.make(v, "La contraseña actual es incorrecta", Snackbar.LENGTH_SHORT).show()
            }

        }
    }


}