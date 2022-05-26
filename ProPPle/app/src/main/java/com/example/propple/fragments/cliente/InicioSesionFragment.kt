package com.example.propple.fragments.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.InicioSesionFragmentBinding
import com.example.propple.viewModel.cliente.InicioSesionViewModel
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

        binding.btnVerContra1.setOnClickListener {
            if ( binding.InContrasenia.inputType==129){
                binding.InContrasenia.inputType=1
            }else if( binding.InContrasenia.inputType==1){
                binding.InContrasenia.inputType=129
            }else{
                binding.InContrasenia.inputType=129
            }

        }

        binding.btnOlvide.setOnClickListener {
            val action = InicioSesionFragmentDirections.actionInicioSesionFragmentToRecuperarCuentaFragment()
            nav(action)
        }

        binding.btnIniciarSesiN.setOnClickListener {
            viewModel.login(binding.InMail.text.toString(),binding.InContrasenia.text.toString())

        }

    }
    fun nav( action: NavDirections){
        v.findNavController().navigate(action)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InicioSesionViewModel::class.java)

        viewModel.rol.observe(viewLifecycleOwner, Observer { result->
            if (result.toString()!=null && result.toString()!=""){

                if (result.toString()=="cliente-prestador"){
                    val action = InicioSesionFragmentDirections.actionInicioSesionFragmentToMainActivityUsuarioPrestador()
                    nav(action)
                }else if (result.toString()=="cliente"){
                    val action = InicioSesionFragmentDirections.actionInicioSesionFragmentToMainActivity2()
                    nav(action)
                }else{
                    Toast.makeText(context,"err",Toast.LENGTH_SHORT)
                }

            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {result->
            Snackbar.make(v,result.toString(), Snackbar.LENGTH_SHORT).show()
        })


    }

}