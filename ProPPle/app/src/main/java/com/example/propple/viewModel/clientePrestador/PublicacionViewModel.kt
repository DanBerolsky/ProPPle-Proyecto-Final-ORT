package com.ort.casodeusotest.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.publication.ChangeVisibility
import com.example.propple.api.publication.Publication
import com.example.propple.api.publication.PublicationCuenta
import com.example.propple.shared.ProPPle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PublicacionViewModel : ViewModel() {
    var status = MutableLiveData<Boolean>()
    var status2 = MutableLiveData<Boolean>()
    var publi = MutableLiveData<Publication>()
    fun getPublicationForPrestador(id: Int) {
        val jwt = ProPPle.prefs.getJwt()
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Publication> = RetrofitHelper.getRetrofit().create(
                PublicationService::class.java).
            getPublicationForPrestador(jwt,id)
            if(call.isSuccessful){
                status.postValue(true)
                publi.postValue(call.body())
            }else{
                Log.i("hola","errror")
                status.postValue(false)
            }
        }
    }

    fun changeVisibility(id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<Void> = RetrofitHelper.getRetrofit().create(
                PublicationService::class.java).changeVisibility(ChangeVisibility(ProPPle.prefs.getJwt(),id))
            if(call.isSuccessful){

            }else{
                status2.postValue(false)
            }
        }
    }
}