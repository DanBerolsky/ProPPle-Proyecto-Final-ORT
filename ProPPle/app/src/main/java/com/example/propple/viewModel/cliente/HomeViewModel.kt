package com.example.propple.viewModel.cliente

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.UserClient.Sign
import com.example.propple.api.interfaces.Publication
import com.example.propple.api.interfaces.UserClientService
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var publiXRubro=MutableLiveData<List<Publication>?>()

    fun getPublications(rubro:String){

        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<List<Publication>> = RetrofitHelper.getRetrofit().create(Publication::class.java).getPublications(
                prefs.getJwt(), prefs.getMenorPrecioBase(), prefs.getMejorValoracion(), prefs.getDireccion(),rubro)
            if(call.isSuccessful){
                val response : List<Publication>? = call.body()
                if (response!=null){
                    publiXRubro.postValue(response)
                }

            }else{
                Log.i("hola","errror")
            }


        }
    }
}