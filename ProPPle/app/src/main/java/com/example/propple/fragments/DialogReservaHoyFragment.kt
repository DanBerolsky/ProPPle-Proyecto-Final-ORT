package com.example.propple.fragments

import android.app.AlertDialog
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
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.propple.R
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.viewModel.DialogReservaHoyViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class DialogReservaHoyFragment() : DialogFragment() {

    private lateinit var viewModel: DialogReservaHoyViewModel
    private lateinit var v:View
    private lateinit var trx:Transaccion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_dialog_reserva_hoy, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()

        //Log.i("ddddddddddddddd",trx.toString())

    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DialogReservaHoyViewModel::class.java)
        if(prefs.getTrx()==""){
            delet()
        }else{
            val json= prefs.getTrx()
            val gson = Gson()
            trx= gson.fromJson(json.toString(), Transaccion::class.java)
            Log.i("ddddddddddddddd",trx.toString())
            v.findViewById<TextView>(R.id.textView18).text= trx.location
        }

        v.findViewById<Button>(R.id.btnSi).setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("¿Estas seguro?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    viewModel.finalizarTransaccion(trx.id_transaccion)
                    delet()
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }

            val alert = builder.create()
            alert.show()
        }


        /*else{
            dialog?.dismiss()
            Snackbar.make(v,"Error",Snackbar.LENGTH_SHORT).show()
        }*/



        v.findViewById<Button>(R.id.btnNo).setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("¿Estas seguro?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                    viewModel.deleteTransaccionPresupuestada(trx.id_transaccion)
                    delet()
                    dialog.dismiss()

                }
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }

            val alert = builder.create()
            alert.show()

        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it) {
                Snackbar.make(v, "Trabajo finalizado", Snackbar.LENGTH_SHORT).show()
                dialog?.dismiss()
            }
        })
        viewModel.status2.observe(viewLifecycleOwner, Observer {
            if (it){
                Snackbar.make(v,"Trabajo Cancelado",Snackbar.LENGTH_SHORT).show()
                dialog?.dismiss()
            }else{
                dialog?.dismiss()
                Snackbar.make(v,"Error",Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    fun delet(){
        dialog?.dismiss()
    }

}