package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.viewModel.cliente.ServicioValoradoViewModel

class ServicioValoradoFragment : Fragment() {

    companion object {
        fun newInstance() = ServicioValoradoFragment()
    }

    private lateinit var viewModel: ServicioValoradoViewModel
    lateinit var v:View
    lateinit var mainHandler: Handler
    var delay = 100001

    private val updateTextTask = object : Runnable {
        override fun run() {
            mainHandler.postDelayed(this, 100001)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(updateTextTask)
        v=inflater.inflate(R.layout.fragment_servicio_valorado, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            v.findNavController().navigate(ServicioValoradoFragmentDirections.actionServicioValoradoFragmentToHomeFragment())
        }, 3000)
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateTextTask)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateTextTask)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ServicioValoradoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}