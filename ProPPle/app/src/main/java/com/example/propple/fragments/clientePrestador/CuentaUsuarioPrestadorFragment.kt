package com.example.propple.fragments.clientePrestador

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.CuentaUsuarioPrestadorFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.viewModel.clientePrestador.CuentaUsuarioPrestadorViewModel

class CuentaUsuarioPrestadorFragment : Fragment() {

    companion object {
        fun newInstance() = CuentaUsuarioPrestadorFragment()
    }

    private lateinit var viewModel: CuentaUsuarioPrestadorViewModel
    private lateinit var v : View
    private lateinit var binding : CuentaUsuarioPrestadorFragmentBinding
    private var nombre : String= prefs.getNombre()
    private var apellido : String= prefs.getApellido()
    private var alias : String= prefs.getAlias()
    private val rol : String = prefs.getRol()
    private lateinit var bntMisPublicaciones : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.cuenta_usuario_prestador_fragment, container, false)
        binding = CuentaUsuarioPrestadorFragmentBinding.bind(v)
        bntMisPublicaciones = v.findViewById(R.id.bntMisPublicaciones)
        nombre=prefs.getNombre()
        apellido=prefs.getApellido()
        alias=prefs.getAlias()
        if (prefs.getUrlImageString()!="")
            binding.btnAvatar.setImageBitmap(prefs.getUrlImage())
        return v
    }

    override fun onStart() {
        super.onStart()
        binding.NombreDeUsuario.setText(nombre+" "+apellido)
        binding.aliasRol.setText(alias +" - " + rol)
        binding.bntDatosPersonales.setOnClickListener { nav(CuentaUsuarioPrestadorFragmentDirections.actionCuentaUsuarioPrestadorFragmentToDatosPersonalesEditFragment2()) }
        binding.bntCambiarContra.setOnClickListener { nav(CuentaUsuarioPrestadorFragmentDirections.actionCuentaUsuarioPrestadorFragmentToCambiarContraseniaPrestadorFragment()) }
        binding.bntMisPreferencias.setOnClickListener { nav(CuentaUsuarioPrestadorFragmentDirections.actionCuentaUsuarioPrestadorFragmentToMisPreferenciasFragment2()) }
        binding.bntPostularme.setOnClickListener { nav(CuentaUsuarioPrestadorFragmentDirections.actionCuentaFragmentToPostularmeFragment()) }
        //binding.bntMisPublicaciones.setOnClickListener { nav(CuentaUsuarioPrestadorFragmentDirections.) }
        bntMisPublicaciones.setOnClickListener {
            val action = CuentaUsuarioPrestadorFragmentDirections.actionCuentaUsuarioPrestadorFragmentToMisPublicacionesFragment()
            v.findNavController().navigate(action)
        }
    }

    private fun nav(action : NavDirections) {
        v.findNavController().navigate(action)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuentaUsuarioPrestadorViewModel::class.java)

        //viewModel.cerrar.observe(viewLifecycleOwner, Observer {result->

          //  if(result){
            //    val action = cambiarContraseniaFragmentDirections.actionCambiarContraseniaFragment2ToMainActivity3()
             //   v.findNavController().navigate(action)
            //}
        //})
    }

}