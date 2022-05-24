package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.propple.viewModel.DatosPersonalesEditViewModel
import com.example.propple.R
import com.example.propple.databinding.DatosPersonalesEditFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs

class datosPersonalesEditFragment : Fragment() {

    companion object {
        fun newInstance() = datosPersonalesEditFragment()
    }

    private lateinit var viewModel: DatosPersonalesEditViewModel
    private lateinit var binding : DatosPersonalesEditFragmentBinding
    private lateinit var v : View
    private var fechaDeNacimiento:String=prefs.getFechaDeNacimiento()
    private var phone:String= prefs.getphone()
    private var dir :String= prefs.getDireccion()
    private var nombre:String=prefs.getNombre()
    private var apellido:String=prefs.getApellido()
    private var alias:String=prefs.getAlias()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.datos_personales_edit_fragment, container, false)
        binding=DatosPersonalesEditFragmentBinding.bind(v)
        return v
    }

    override fun onStart() {
        super.onStart()
        binding.Nombre.setText(nombre+" "+apellido)
        binding.aliasRoll.setText(alias+ " - "+ prefs.getRol())
        binding.btnDate.setOnClickListener { showDatePickerDialog() }
        binding.InDirecion.setText(dir)
        binding.InFechaDeNacrimiento.setText(fechaDeNacimiento)
        binding.InTelefono.setText(phone)
        binding.InNombre.setText(nombre)
        binding.InApellido.setText(apellido)
        binding.InAlias.setText(alias)
        binding.btnGuardar.setOnClickListener { viewModel.updateUser(
                                                                    prefs.getJwt(),
                                                                    binding.InAlias.text.toString(),
                                                                    binding.InFechaDeNacrimiento.text.toString(),
                                                                    "f",
                                                                    binding.InDirecion.text.toString(),
                                                                    binding.InDirecion.text.toString().split(",").toTypedArray()[0].toDouble(),
                                                                    binding.InDirecion.text.toString().split(",").toTypedArray()[1].toDouble(),
                                                                    phone,
                                                                    prefs.getUrlImage(),
                                                                    prefs.getApellido(),
                                                                    prefs.getNombre(),v) }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        datePicker.show(fragmentManager, "RegistroUsuarioFragment")
    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.InFechaDeNacrimiento.setText("$day / $month / $year")
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatosPersonalesEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}