package com.example.propple.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.example.propple.shared.ProPPle.Companion.prefs
import com.google.android.gms.location.FusedLocationProviderClient

class GoogleMaps {
    val PERMISSION_REQUEST_ACCESS_LOCATION=100
    var dir = MutableLiveData<String>()
    var lon = MutableLiveData<Double>()
    var lat = MutableLiveData<Double>()
    fun getCurrentLocation(acti:Activity,context:Context,fusedLocationClient : FusedLocationProviderClient):Boolean {
         if(checkPermisison(acti)){
            if(isLocationEnable(acti))
            {
                //final latitude and longitude
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(acti)
                }
                fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                    val location: Location?=task.result
                    if (location==null){
                        Toast.makeText(context,"Null recived", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"get Success", Toast.LENGTH_SHORT).show()
                        var latitude=location.latitude
                        var longitude=location.longitude
                        var salida = getAddress(latitude,longitude,context).toString()
                        dir.postValue(salida)
                        lat.postValue(latitude)
                        lon.postValue(longitude)
                        //binding.InDirecion.setText()
                        //binding.InDirecion.setText(latitude.toString()+","+longitude.toString())
                    }
                }


            }else
            {
                // abrir ajustes para acitvar localizacion
                Toast.makeText(context,"Turn on location", Toast.LENGTH_SHORT).show()
                //val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                //startActivity(intent)
            }

        }else{
            //perdir permisos
            requestPermissions(acti)
        }
        return true
    }
    private fun getAddress(lat: Double, lng: Double,context:Context): String? {
        val geocoder = Geocoder(context)
        val list = geocoder.getFromLocation(lat,lng,1)
        return list[0].getAddressLine(0)
    }

    private fun requestPermissions(acti:Activity){
        ActivityCompat.requestPermissions(acti,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    private fun isLocationEnable(acti:Activity): Boolean {
        val locationManager : LocationManager = acti.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    private fun checkPermisison(context:Context): Boolean {
        if(ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }


}