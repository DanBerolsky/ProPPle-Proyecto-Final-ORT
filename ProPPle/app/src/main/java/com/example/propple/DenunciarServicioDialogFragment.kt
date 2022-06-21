package com.example.propple

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.propple.fragments.cliente.publicacionVistaPublicaFragment

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
        v.findViewById<Button>(R.id.btnEnviar).setOnClickListener {
            dialog?.dismiss()
        }
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
        // TODO: Use the ViewModel
    }

}