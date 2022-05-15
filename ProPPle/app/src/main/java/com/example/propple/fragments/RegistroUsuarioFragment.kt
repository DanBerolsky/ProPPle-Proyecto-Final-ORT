package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.api.UserClient.Sign
import com.example.propple.databinding.InicioSesionFragmentBinding
import com.example.propple.databinding.RegistroUsuarioFragmentBinding
import com.example.propple.viewModel.RegistroUsuarioViewModel
import com.google.android.material.snackbar.Snackbar

class RegistroUsuarioFragment : Fragment() {

    private lateinit var v : View
    private lateinit var binding : RegistroUsuarioFragmentBinding


    private lateinit var viewModel: RegistroUsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.registro_usuario_fragment, container, false)
        binding = RegistroUsuarioFragmentBinding.bind(v)
        return v
    }


    override fun onStart() {
        super.onStart()

        binding.btnRegistrarUsuario.setOnClickListener {
            if (binding.InContrasenia1.text.toString() != binding.InContrasenia2.text.toString()){
                Snackbar.make(v,"Ingrese nuevamente la contraseña", Snackbar.LENGTH_SHORT).show()
            }else{
                val userAux : Sign
                userAux = Sign(
                    binding.InAlias.text.toString(),
                    "",
                    "",
                    "",
                    binding.InMail.text.toString(),
                    binding.InTelefono.text.toString(),
                    "",
                    binding.InApellido.text.toString(),
                    binding.InNombre.text.toString(),
                    binding.InContrasenia1.text.toString(),
                    1
                )
                Log.i("hola", userAux.toString())
                viewModel.sign(userAux)
            }

        }

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistroUsuarioViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.status.observe(viewLifecycleOwner, Observer {result->

            if (result.toString()=="ok"){
                val action = RegistroUsuarioFragmentDirections.actionRegistroUsuarioFragmentToValidacionDeCuentaFragment()
                v.findNavController().navigate(action)
            }else{
                Snackbar.make(v,result.toString(), Snackbar.LENGTH_SHORT).show()
            }
        })
    }

}