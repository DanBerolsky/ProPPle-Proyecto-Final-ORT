package com.example.propple.fragments.cliente

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.RecuperarCuentaFragmentBinding
import com.example.propple.databinding.RegistroUsuarioFragmentBinding
import com.example.propple.utils.InputFieldValidator
import com.example.propple.viewModel.cliente.RecuperarCuentaViewModel
import com.google.android.material.snackbar.Snackbar

class RecuperarCuentaFragment : Fragment() {

    companion object {
        fun newInstance() = RecuperarCuentaFragment()
    }

    private lateinit var v: View
    private lateinit var binding : RecuperarCuentaFragmentBinding
    private lateinit var viewModel: RecuperarCuentaViewModel
    private lateinit var btnRecuperarCuenta : Button
    private lateinit var inMail : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.recuperar_cuenta_fragment, container, false)
        binding = RecuperarCuentaFragmentBinding.bind(v)
        btnRecuperarCuenta = v.findViewById(R.id.btnRecuperar)
        inMail = v.findViewById(R.id.InMail)
        return v
    }


    override fun onStart() {
        super.onStart()


        btnRecuperarCuenta.setOnClickListener {
            if(verificarCamposVacios()){
                Snackbar.make(v, "No ingresaste ningún correo electrónico", Snackbar.LENGTH_SHORT).show()
            } else {
                viewModel.getDatosFragment(v.findViewById<EditText>(R.id.InMail).text.toString(),v)
                val action = RecuperarCuentaFragmentDirections.actionRecuperarCuentaFragmentToRecuperarCuenta2Fragment()
                v.findNavController().navigate(action)
            }
        }


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecuperarCuentaViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun verificarCamposVacios(): Boolean {
        var campoVacio = false
        var txvDefaultColor = 1979711488
        if (InputFieldValidator.esCampoVacio(binding.InMail, binding.txvInMailRC, txvDefaultColor) && !campoVacio) campoVacio = true
        return campoVacio
    }

}