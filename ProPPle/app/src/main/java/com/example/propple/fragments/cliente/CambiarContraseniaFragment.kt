package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.propple.viewModel.cliente.CambiarContraseniaViewModel
import com.example.propple.R
import com.example.propple.databinding.CambiarContraseniaFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs

class cambiarContraseniaFragment : Fragment() {

    companion object {
        fun newInstance() = cambiarContraseniaFragment()
    }

    private lateinit var viewModel: CambiarContraseniaViewModel
    private lateinit var v:View
    private lateinit var binding: CambiarContraseniaFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.cambiar_contrasenia_fragment, container, false)
        binding= CambiarContraseniaFragmentBinding.bind(v)
        return v
    }

    override fun onStart() {
        super.onStart()

        binding.btnVerContraActual.setOnClickListener {
            if (binding.InContraseniaActual.inputType==129){
                binding.InContraseniaActual.inputType=1
            }else if(binding.InContraseniaActual.inputType==1){
                binding.InContraseniaActual.inputType=129
            }else{
                binding.InContraseniaActual.inputType=129
            }

        }


        binding.btnVerContra1.setOnClickListener {
            if (binding.InContrasenia1.inputType==129){
                binding.InContrasenia1.inputType=1
            }else if(binding.InContrasenia1.inputType==1){
                binding.InContrasenia1.inputType=129
            }else{
                binding.InContrasenia1.inputType=129
            }

        }
        binding.btnVerContra2.setOnClickListener {

            if (binding.InContrasenia2.inputType==129){
                binding.InContrasenia2.inputType=1
            }else if(binding.InContrasenia1.inputType==1){
                binding.InContrasenia2.inputType=129
            }else{
                binding.InContrasenia2.inputType=129
            }
        }


        binding.btnCambiar.setOnClickListener {
            viewModel.passwordChange(binding.InContraseniaActual.text.toString(),
                binding.InContrasenia1.text.toString(),
                binding.InContrasenia2.text.toString(),
                v)
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CambiarContraseniaViewModel::class.java)

        viewModel.cerrar.observe(viewLifecycleOwner, Observer {result->
            var action : NavDirections= cambiarContraseniaFragmentDirections.actionCambiarContraseniaFragmentToMainActivity()

            if(result && prefs.getRol()=="Cliente"){
                action = cambiarContraseniaFragmentDirections.actionCambiarContraseniaFragmentToMainActivity()
            }
            v.findNavController().navigate(action)
        })

    }

}