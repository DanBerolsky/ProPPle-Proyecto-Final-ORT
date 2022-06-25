package com.example.propple.activities.clientePrestador

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.propple.R
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.RechazarReserva
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.fragments.DialogReservaHoyFragment
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityUsuarioPrestador : AppCompatActivity() {

    private lateinit var bottomNavigationView : BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    lateinit var mainHandler: Handler
    var delay = 20000

    private val updateTextTask = object : Runnable {
        override fun run() {
            delay = 10000
            getTransaccionesAFinalizar()
            mainHandler.postDelayed(this, delay.toLong())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_usuario_prestador)
        mainHandler = Handler(Looper.getMainLooper())
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //call super
        super.onActivityResult(requestCode, resultCode, data)
    }



    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateTextTask)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateTextTask)
    }

    fun getTransaccionesAFinalizar(){
        val token= ProPPle.prefs.getJwt()
        CoroutineScope(Dispatchers.IO).launch {
            val call : Response<List<Transaccion>> = RetrofitHelper.getRetrofit().create(
                Transacciones::class.java).
            getTransaccionesAFinalizar(token)
            val body=call.body()
            if(call.isSuccessful){
                if (body!=null){
                    if (body.isNotEmpty()){
                        val json = prefs.getTrx()
                        if (json!=""){
                            val gson = Gson()
                            val obj= gson.fromJson(json, Transaccion::class.java)
                            if (obj.id_transaccion!=body[0].id_transaccion){
                                ProPPle.prefs.setTrx(body[0])
                                DialogReservaHoyFragment().show(supportFragmentManager,"d")
                                //delay = 60000
                            }
                        }else{
                            ProPPle.prefs.setTrx(body[0])
                            DialogReservaHoyFragment().show(supportFragmentManager,"d")
                            //delay = 60000
                        }

                    }
                }

            }else{

            }
        }

    }

}