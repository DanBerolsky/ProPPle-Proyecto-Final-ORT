package com.example.propple.shared

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    val PREFS_NAME = "myPreferences"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

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

    fun getUrlImage(): String {
        return prefs.getString("urlImage", "").toString()
    }

    fun setUrlImage(x: String) {
        prefs.edit().putString("urlImage", x).apply()
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

}