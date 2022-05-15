package com.example.propple.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.InicioSesionFragmentBinding
import com.example.propple.viewModel.InicioSesionViewModel
import com.google.android.material.snackbar.Snackbar

class InicioSesionFragment : Fragment() {

    private lateinit var v: View
    private lateinit var viewModel: InicioSesionViewModel
    private lateinit var binding: InicioSesionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.inicio_sesion_fragment, container, false)
        binding = InicioSesionFragmentBinding.bind(v)
        return v
    }


    override fun onStart() {
        super.onStart()


        binding.btnOlvide.setOnClickListener {
            val action = InicioSesionFragmentDirections.actionInicioSesionFragmentToRecuperarCuentaFragment()
            v.findNavController().navigate(action)
        }

        binding.btnIniciarSesiN.setOnClickListener {
            viewModel.login(binding.InMail.text.toString(),binding.InContrasenia.text.toString())

        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InicioSesionViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.rol.observe(viewLifecycleOwner, Observer { result ->


            if (result.toString()!=null && result.toString()!=""){
                var action = InicioSesionFragmentDirections.actionInicioSesionFragmentToMainActivity2()
                if (result.toString()=="cliente-prestador"){
                    action = InicioSesionFragmentDirections.actionInicioSesionFragmentToMainActivityUsuarioPrestador()
                }
                v.findNavController().navigate(action)
            }

        })

        viewModel.status.observe(viewLifecycleOwner, Observer {result->

            Snackbar.make(v,result.toString(), Snackbar.LENGTH_SHORT).show()
        })


    }

}