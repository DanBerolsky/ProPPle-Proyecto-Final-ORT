package com.example.propple.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.publication.Denuncia
import com.example.propple.shared.ProPPle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DenunciarServicioDialogViewModel : ViewModel() {
    var status = MutableLiveData<Boolean>()
    fun getPublicationsForPrestador(id_usuario_prestador:Int){
        val jwt = ProPPle.prefs.getJwt()
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Void> = RetrofitHelper.getRetrofit().create(
                PublicationService::class.java).
            denuncia(Denuncia(jwt, id_usuario_prestador))
            if(call.isSuccessful){
                status.postValue(true)
            }else{
                Log.i("hola","errror")
                status.postValue(false)
            }
        }
    }
}