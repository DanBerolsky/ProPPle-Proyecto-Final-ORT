package com.ort.casodeusotest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.publication.ChangeVisibility
import com.example.propple.api.publication.UpdatePublication
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PublicarServicioViewModel : ViewModel() {
    var status2 = MutableLiveData<Boolean>()
    fun publicarServicio(id:Int,location: String,
                         location_latitud: Double,
                         location_longitud: Double,
                         precio_x_hora: Int,
                         publicacion_description: String,
                         title: String){
        val token=prefs.getJwt()
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Void> = RetrofitHelper.getRetrofit().create(
                PublicationService::class.java).updatePublication(UpdatePublication(location,
                location_latitud,location_longitud,precio_x_hora,publicacion_description,title,token,id))
            if(call.isSuccessful){
                status2.postValue(true)
            }else{
                status2.postValue(false)
            }
        }
    }
}