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
    var delay = 3000

    private val updateTextTask = object : Runnable {
        override fun run() {
            v.findNavController().navigate(ServicioValoradoFragmentDirections.actionServicioValoradoFragmentToHomeFragment())
            mainHandler.postDelayed(this, delay.toLong())
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainHandler = Handler(Looper.getMainLooper())
        v=inflater.inflate(R.layout.fragment_servicio_valorado, container, false)
        return v
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