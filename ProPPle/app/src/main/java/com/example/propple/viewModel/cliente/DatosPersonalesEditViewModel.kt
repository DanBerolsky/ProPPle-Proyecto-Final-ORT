package com.example.propple.viewModel.cliente

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.UserClient.UpdateUser
import com.example.propple.api.interfaces.UserClientService
import com.example.propple.shared.ProPPle.Companion.prefs
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DatosPersonalesEditViewModel : ViewModel() {
    fun updateUser(token:String,
                   alias: String,
                   date_of_birth: String,
                   gender: String,
                   location: String,
                   location_latitud: Double,
                   location_longitud: Double,
                   phone: String,
                   url_image: String,
                   user_last_name: String,
                   user_name: String,v: View) {
        val updateUserAux=UpdateUser(token,alias, date_of_birth, gender, location, location_latitud, location_longitud, phone, url_image, user_last_name, user_name)
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Void> = RetrofitHelper.getRetrofit().create(UserClientService::class.java).updateUser(updateUserAux)
            if(call.isSuccessful){
                prefs.setAlias(alias)
                prefs.setFechaDeNacimiento(date_of_birth)
                prefs.setGenero(gender)
                prefs.setDireccion(location)
                prefs.setphone(phone)
                prefs.setUrlImage(url_image)
                prefs.setNombre(user_name)
                prefs.setApellido(user_last_name)

            }else{
                Snackbar.make(v,"ERROR",Snackbar.LENGTH_SHORT).show()
            }


        }
    }


}