package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.viewModel.cliente.FormalizacionEnvioViewModel

class FormalizacionEnvioFragment : Fragment() {

    companion object {
        fun newInstance() = FormalizacionEnvioFragment()
    }

    private lateinit var v : View
    private lateinit var btnHome2 : Button
    private lateinit var viewModel: FormalizacionEnvioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_formalizacion_envio, container, false)
        btnHome2 = v.findViewById(R.id.btnHome2)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnHome2.setOnClickListener {
            //val action = FormalizacionEnvioFragmentDirections.actionFormalizacionEnvioFragmentToHomeFragment()
            //v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FormalizacionEnvioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}