package com.example.propple.shared

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    val PREFS_NAME = "myPreferences"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    fun setJwt(jwt : String){
        prefs.edit().putString("jwt",jwt).apply()
    }
    fun getJwt():String{
        return prefs.getString("jwt","").toString()
    }
    fun setRol(rol : String){
        prefs.edit().putString("rol",rol).apply()
    }
    fun getRol() : String{
        return prefs.getString("rol","").toString()
    }

    fun setNombre(nombre : String){
        prefs.edit().putString("nombre",nombre).apply()
    }
    fun getNombre() : String{
        return prefs.getString("nombre","").toString()
    }
    fun setAlias(alias : String){
        prefs.edit().putString("alias",alias).apply()
    }
    fun getAlias() : String{
        return prefs.getString("alias","").toString()
    }

    fun setDireccion(Direccion : String){
        prefs.edit().putString("direccion",Direccion).apply()
    }
    fun getDireccion() : String{
        return prefs.getString("direccion","").toString()
    }

}