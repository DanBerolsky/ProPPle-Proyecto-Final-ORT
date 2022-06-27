package com.example.propple.fragments.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.CambiarContraseniaFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.utils.InputFieldValidator
import com.example.propple.utils.InputFieldValidator.Companion.esContraseniaCompleja
import com.example.propple.viewModel.cliente.CambiarContraseniaViewModel
import com.google.android.material.snackbar.Snackbar

class cambiarContraseniaFragment : Fragment() {

    private lateinit var viewModel: CambiarContraseniaViewModel
    private lateinit var v:View
    private lateinit var binding: CambiarContraseniaFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.cambiar_contrasenia_fragment, container, false)
        binding= CambiarContraseniaFragmentBinding.bind(v)
        return v    }

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
            if(verificarCamposVacios()) {
                Snackbar.make(v, "Los campos con * son obligatorios", Snackbar.LENGTH_SHORT).show()
            }
            else if (!esContraseniaCompleja(binding.InContrasenia1, binding.txvInContrasenia1CC, 1979711488)){
                Snackbar.make(v, "La contraseña debe contener 8 caracteres cómo mínimo, incluyendo al menos una letra minúscula, otra mayúscula, un número y un caracter especial.", Snackbar.LENGTH_SHORT).show()
            }
            else if (!esContraseniaCompleja(binding.InContrasenia2, binding.txvInContrasenia2CC, 1979711488)){
                Snackbar.make(v, "La contraseña debe contener 8 caracteres cómo mínimo, incluyendo al menos una letra minúscula, otra mayúscula, un número y un caracter especial.", Snackbar.LENGTH_SHORT).show()
            }
            else {
                viewModel.passwordChange(binding.InContraseniaActual.text.toString(),
                    binding.InContrasenia1.text.toString(),
                    binding.InContrasenia2.text.toString(),
                    v)
            }
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

    private fun verificarCamposVacios(): Boolean {
        var campoVacio = false
        var txvDefaultColor = 1979711488
        if (InputFieldValidator.esCampoVacio(binding.InContraseniaActual, binding.txvInContraseniaActualCC, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InContrasenia1, binding.txvInContrasenia1CC, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InContrasenia2, binding.txvInContrasenia2CC, txvDefaultColor) && !campoVacio) campoVacio = true
        return campoVacio
    }

}