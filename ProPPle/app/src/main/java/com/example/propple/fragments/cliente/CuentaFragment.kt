package com.example.propple.fragments.cliente

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.CuentaFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.utils.imgController.getImgUrl
import com.example.propple.viewModel.cliente.CuentaViewModel
import com.google.android.material.snackbar.Snackbar

class CuentaFragment : Fragment() {

    companion object {
        fun newInstance() = CuentaFragment()
    }

    private var apellido: String=""
    private lateinit var viewModel: CuentaViewModel
    private lateinit var v : View
    private lateinit var bntDatosPersonales : Button
    private lateinit var bntCambiarContra : Button
    private lateinit var bntMisPreferencias : Button
    private lateinit var bntPostularme : Button
    private lateinit var binding : CuentaFragmentBinding
    private  var nombre : String=prefs.getNombre()
    private  var alias : String= prefs.getAlias()
    private  var rol : String=prefs.getRol()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.cuenta_fragment, container, false)
        bntDatosPersonales = v.findViewById(R.id.bntDatosPersonales)
        bntCambiarContra = v.findViewById(R.id.bntCambiarContra)
        bntMisPreferencias = v.findViewById(R.id.bntMisPreferencias)
        bntPostularme = v.findViewById(R.id.bntPostularme)
        binding = CuentaFragmentBinding.bind(v)
        nombre=prefs.getNombre()
        apellido=prefs.getApellido()
        alias=prefs.getAlias()
        if (prefs.getUrlImageString()!=""){
            Log.i("queonda", prefs.getUrlImageString())
            //Snackbar.make(v,prefs.getUrlImageString()+"", Snackbar.LENGTH_SHORT).show()
            getImgUrl(prefs.getUrlImageString(),requireContext(),binding.btnAvatar)
        }

        return v
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        binding.NombreDeUsuario.setText(nombre+" "+ apellido)
        binding.aliasRol.setText("$alias - $rol")
        bntDatosPersonales.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToDatosPersonalesEditFragment()
            nav(action)
        }
        bntCambiarContra.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToCambiarContraseniaFragment()
            nav(action)
        }
        bntMisPreferencias.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToMisPreferenciasFragment()
            nav(action)
        }
        bntPostularme.setOnClickListener {
            val action = CuentaFragmentDirections.actionCuentaFragmentToPostularmeFragment()
            nav(action)
        }

    }

    private fun nav(action : NavDirections) {
        v.findNavController().navigate(action)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuentaViewModel::class.java)


    }

}