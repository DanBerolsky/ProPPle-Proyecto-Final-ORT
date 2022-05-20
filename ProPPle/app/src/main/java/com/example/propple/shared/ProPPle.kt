package com.example.propple.shared

import android.app.Application

class ProPPle : Application() {

    companion object{
        lateinit var prefs : Prefs
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}