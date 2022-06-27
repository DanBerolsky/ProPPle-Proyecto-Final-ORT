package com.example.propple.activities.cliente

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.propple.R
import com.example.propple.activities.clientePrestador.MainActivityUsuarioPrestador
import com.example.propple.shared.ProPPle.Companion.prefs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs.clear()
    }

    override fun onStart() {
        super.onStart()


        prefs.clear()
    }

    override fun onResume() {
        super.onResume()
        prefs.clear()
    }
}