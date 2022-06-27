package com.example.propple.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.propple.R
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.viewModel.DenunciarServicioDialogViewModel
import com.example.propple.viewModel.cliente.PublicacionVistaPublicaViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class DenunciarServicioDialogFragment : DialogFragment() {

    companion object {
        fun newInstance() = DenunciarServicioDialogFragment()
    }

    private lateinit var viewModel: DenunciarServicioDialogViewModel
    private lateinit var v:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.fragment_denunciar_servicio_dialog, container, false)

        return v
    }


    override fun onStart() {
        super.onStart()

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DenunciarServicioDialogViewModel::class.java)

        Log.i("aaaaaaa", prefs.getPubli())
        val json = prefs.getTrx()
        val gson = Gson()
        val obj= gson.fromJson(json, Transaccion::class.java)
        v.findViewById<Button>(R.id.btnSi).setOnClickListener {
            viewModel.getPublicationsForPrestador(obj.id_usuario_prestador,v.findViewById<EditText>(R.id.InMotivo).text.toString())
        }
        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it){
                dialog?.dismiss()
            }else{
                Snackbar.make(v,"ERROR",Snackbar.LENGTH_SHORT).show()
            }
        })

    }

}