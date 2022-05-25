package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.example.propple.viewModel.cliente.DatosPersonalesEditViewModel
import com.example.propple.R
import com.example.propple.databinding.DatosPersonalesEditFragmentBinding
import com.example.propple.shared.ProPPle.Companion.prefs
import com.google.android.material.snackbar.Snackbar

class datosPersonalesEditFragment : Fragment() {

    companion object {
        fun newInstance() = datosPersonalesEditFragment()
    }

    private lateinit var viewModel: DatosPersonalesEditViewModel
    private lateinit var binding : DatosPersonalesEditFragmentBinding
    private lateinit var v : View
    private var fechaDeNacimiento:String=""
    private var phone:String=""
    private var dir :String=""
    private var nombre:String=""
    private var apellido:String=""
    private var alias:String=""
    private var genero:String=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.datos_personales_edit_fragment, container, false)
        binding=DatosPersonalesEditFragmentBinding.bind(v)
        fechaDeNacimiento=prefs.getFechaDeNacimiento()
        phone=prefs.getphone()
        dir=prefs.getDireccion()
        nombre=prefs.getNombre()
        apellido=prefs.getApellido()
        alias=prefs.getAlias()
        genero=prefs.getGenero()
        return v
    }

    override fun onStart() {
        super.onStart()
        binding.inGenero.setText(genero)
        binding.btnGenero.setOnClickListener {
            binding.spinner2.performClick()
            binding.spinner2.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    genero= getResources().getStringArray(R.array.generos)[position]
                    Snackbar.make(v,genero,Snackbar.LENGTH_SHORT).show()
                    binding.inGenero.setText(genero)
                }
            }

        }





        binding.Nombre.setText(nombre+" "+apellido)
        binding.aliasRoll.setText(alias+ " - "+ prefs.getRol())
        binding.btnDate1.setOnClickListener { showDatePickerDialog() }
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
                                                                    genero,
                                                                    binding.InDirecion.text.toString(),
                                                                    0.0,
                                                        0.0,
                                                                    binding.InTelefono.text.toString(),
                                                                    prefs.getUrlImage(),
                                                                    binding.InApellido.text.toString(),
                                                                    binding.InNombre.text.toString(),v ,binding) }

        //binding.InDirecion.text.toString().split(",").toTypedArray()[0].toDouble(),
        //binding.InDirecion.text.toString().split(",").toTypedArray()[1].toDouble(),
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
        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (!it){
                binding.inGenero.setText(genero)
                binding.InDirecion.setText(dir)
                binding.InFechaDeNacrimiento.setText(fechaDeNacimiento)
                binding.InTelefono.setText(phone)
                binding.InNombre.setText(nombre)
                binding.InApellido.setText(apellido)
                binding.InAlias.setText(alias)
            }else{
                nombre=prefs.getNombre()
                apellido=prefs.getApellido()
                alias=prefs.getAlias()
                binding.Nombre.setText(nombre+" "+apellido)
                binding.aliasRoll.setText(alias+ " - "+ prefs.getRol())
            }
        })
    }

}