package com.ort.casodeusotest.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.propple.R
import com.example.propple.databinding.PublicarServicioFragmentBinding
import com.example.propple.databinding.RegistroUsuarioFragmentBinding
import com.example.propple.utils.InputFieldValidator
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.viewModel.PublicarServicioViewModel

class PublicarServicioFragment : Fragment() {

    companion object {
        fun newInstance() = PublicarServicioFragment()
    }

    private lateinit var v : View
    private lateinit var binding : PublicarServicioFragmentBinding
    private lateinit var btnPublicar : Button
    private lateinit var fabVolverPublicacion1 : FloatingActionButton

    private lateinit var viewModel: PublicarServicioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.publicar_servicio_fragment, container, false)
        binding = PublicarServicioFragmentBinding.bind(v)
        btnPublicar = v.findViewById(R.id.btnEnviarReserva)
        fabVolverPublicacion1 = v.findViewById(R.id.fabVolverPublicacion1)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnPublicar.setOnClickListener {
            if(verificarCamposVacios()) {
                Snackbar.make(v, "Los campos con * son obligatorios", Snackbar.LENGTH_SHORT).show()
            } else {
                val action = PublicarServicioFragmentDirections.actionPublicarServicioFragmentToPublicacionFragment(PublicarServicioFragmentArgs.fromBundle(requireArguments()).rubroPosition)
                v.findNavController().navigate(action)
                Snackbar.make(v, "Publicación modificada", Snackbar.LENGTH_SHORT).show()
            }
        }
        fabVolverPublicacion1.setOnClickListener {
            val action = PublicarServicioFragmentDirections.actionPublicarServicioFragmentToPublicacionFragment(PublicarServicioFragmentArgs.fromBundle(requireArguments()).rubroPosition)
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicarServicioViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun verificarCamposVacios(): Boolean {
        var campoVacio = false
        var txvDefaultColor = 1979711488
        if (InputFieldValidator.esCampoVacio(binding.InTituloServicio, binding.txvInTituloServicioPS, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InDescripcionServicio, binding.txvInDescripcionServicioPS, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InPrecioHora, binding.txvInPrecioHoraPS, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InDireccionLaboral, binding.txvInDireccionLaboralPS, txvDefaultColor) && !campoVacio) campoVacio = true
        return campoVacio
    }

}