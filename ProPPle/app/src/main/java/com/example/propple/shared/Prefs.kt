package com.example.propple.shared

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.utils.imgController
import com.google.gson.Gson


class Prefs(context: Context) {
    val PREFS_NAME = "myPreferences"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    fun setTrx(trx: Transaccion) {
        val prefsEditor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(trx)
        prefsEditor.putString("trx", json)
        prefsEditor.apply()
    }

    fun getTrx(): String {
        val gson = Gson()
        val json= prefs.getString("trx", null)
        //val obj=null
        //if (json!=null){
        //    val obj= gson.fromJson(json.toString(), Transaccion::class.java)
       // }
       // return obj
        return prefs.getString("trx", "").toString()
    }

    fun setJwt(jwt: String) {
        prefs.edit().putString("jwt", jwt).apply()
    }

    fun getJwt(): String {
        return prefs.getString("jwt", "").toString()
    }

    fun setRol(rol: String) {
        prefs.edit().putString("rol", rol).apply()
    }

    fun getRol(): String {
        return prefs.getString("rol", "").toString()
    }

    fun setNombre(nombre: String) {
        prefs.edit().putString("nombre", nombre).apply()
    }

    fun getNombre(): String {
        return prefs.getString("nombre", "").toString()
    }

    fun setAlias(alias: String) {
        prefs.edit().putString("alias", alias).apply()
    }

    fun getAlias(): String {
        return prefs.getString("alias", "").toString()
    }

    fun setLat(Direccion: Double) {
        prefs.edit().putString("lat", Direccion.toString()).apply()
    }

    fun getLat(): Double {
        return prefs.getString("lat", "0.0")?.toDouble() ?: 0.0
    }

    fun setLon(Direccion: Double) {
        prefs.edit().putString("lon", Direccion.toString()).apply()
    }

    fun getLon(): Double {
        return prefs.getString("lon", "0.0")?.toDouble() ?: 0.0
    }
    fun setDireccion(Direccion: String) {
        prefs.edit().putString("direccion", Direccion).apply()
    }

    fun getDireccion(): String {
        return prefs.getString("direccion", "").toString()
    }

    fun setFechaDeNacimiento(fecha: String) {
        prefs.edit().putString("fecha", fecha).apply()
    }

    fun getFechaDeNacimiento(): String {
        return prefs.getString("fecha", "DD / MM / AAAA").toString()
    }

    fun setphone(phone: String) {
        prefs.edit().putString("phone", phone).apply()
    }

    fun getphone(): String {
        return prefs.getString("phone", "").toString()
    }

    fun setApellido(ap: String) {
        prefs.edit().putString("apellido", ap).apply()
    }

    fun getApellido(): String {
        return prefs.getString("apellido", "").toString()
    }

    fun getUrlImage(): Bitmap {
        return imgController.base64decode( prefs.getString("urlImage", "").toString())
    }
    fun setUrlImageString(img : String){
        prefs.edit().putString("urlImage", img).apply()
    }
    fun getUrlImageString():String{
        return prefs.getString("urlImage", "").toString()
    }

    fun setUrlImage(uri: Uri,context: Context) {
        prefs.edit().putString("urlImage",
            imgController.base64Encode(uri, context)).apply()
    }

    fun setGenero(gender: String) {
        prefs.edit().putString("gender", gender).apply()
    }

    fun getGenero(): String {
        return prefs.getString("gender", "Sin especificar").toString()
    }

    fun clear(){
        prefs.edit().clear().apply()
    }
    fun setMejorValoracion(mejorValoracion: Boolean) {
        prefs.edit().putBoolean("mejorValoracion", mejorValoracion).apply()
    }

    fun getMejorValoracion(): Boolean {
        return prefs.getBoolean("mejorValoracion", false)
    }
    fun setMenorPrecioBase(menorPrecioBase: Boolean) {
        prefs.edit().putBoolean("menorPrecioBase", menorPrecioBase).apply()
    }

    fun getMenorPrecioBase(): Boolean {
        return prefs.getBoolean("menorPrecioBase", false)
    }


}