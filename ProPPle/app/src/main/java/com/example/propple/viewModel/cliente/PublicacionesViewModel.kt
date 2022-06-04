package com.example.propple.viewModel.cliente

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.publication.Publication
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PublicacionesViewModel : ViewModel() {
    var publiXRubro= MutableLiveData<List<Publication>?>()

    fun getPublications(rubro:String){

        CoroutineScope(Dispatchers.IO).launch {
            var call : Response<List<Publication>> = RetrofitHelper.getRetrofit().create(PublicationService::class.java).getPublications(
                ProPPle.prefs.getJwt(), ProPPle.prefs.getMenorPrecioBase(), ProPPle.prefs.getMejorValoracion(), ProPPle.prefs.getLat().toString()+","+prefs.getLon().toString(),rubro)
            Log.i("hola",ProPPle.prefs.getJwt()+ ProPPle.prefs.getMenorPrecioBase()+ ProPPle.prefs.getMejorValoracion()+ ProPPle.prefs.getLat().toString()+prefs.getLon().toString()+rubro)
            if(call.isSuccessful){
                val response : List<Publication>? = call.body()
                if (response!=null){
                    Log.i("hola",ProPPle.prefs.getJwt().toString()+"/"+ ProPPle.prefs.getMenorPrecioBase().toString()+"/"+ ProPPle.prefs.getMejorValoracion().toString()+"/"+ ProPPle.prefs.getDireccion().toString()+"/"+rubro.toString())
                    Log.i("hola",response.toString())
                    publiXRubro.postValue(response)
                }else{
                    publiXRubro.postValue(listOf())
                }

            }else{
                Log.i("hola","errror")
            }


        }
    }}