package com.example.propple.adapters.clientePrestador.reservas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.api.RetrofitHelper
import com.example.propple.api.Transacciones.RechazarReserva
import com.example.propple.api.Transacciones.Transaccion
import com.example.propple.api.interfaces.PublicationService
import com.example.propple.api.interfaces.Transacciones
import com.example.propple.fragments.clientePrestador.ReservasFragmentDirections
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.utils.imgController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response


class ReservaSolicitadaAdapter(
    var reservaList: List<Transaccion>/*,
                                var onClick : (Int) -> Unit*/) : RecyclerView.Adapter<ReservaSolicitadaAdapter.ReservaHolder>() {

    class ReservaHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View

        init {
            this.view = v
        }

        /*fun getCard(): CardView {
            return view.findViewById(R.id.cardReservaDetalleItem)
        }*/
        fun setAvatar(img64:String){
            if (ProPPle.prefs.getUrlImageString()!="")
                imgController.getImgUrl(
                    img64,
                    view.context,
                    view.findViewById<ImageView>(R.id.btnAvatar)
                )
        }

        fun edit(trx: Transaccion) {
            view.findViewById<FloatingActionButton>(R.id.fabLlenarFormulario).setOnClickListener{
                view.findNavController().navigate( ReservasFragmentDirections.actionReservasFragment2ToFormularioReservaFragment2(trx))
            }
        }
        fun cancelar(id: Int){
            view.findViewById<FloatingActionButton>(R.id.fabRechazarSolicitud).setOnClickListener{
                view.findViewById<FloatingActionButton>(R.id.fabRechazarSolicitud).visibility=View.GONE
                view.findViewById<FloatingActionButton>(R.id.fabLlenarFormulario).visibility=View.GONE

                CoroutineScope(Dispatchers.IO).launch {
                    var call : Response<Void> = RetrofitHelper.getRetrofit().create(
                        Transacciones::class.java).deleteTransaccionIniciada(RechazarReserva(prefs.getJwt(),id))
                    if(call.isSuccessful){
                        async{  Snackbar.make(view,"Solicitud Rechazada",Snackbar.LENGTH_SHORT).show()}
                        //
                    }else {
                        async{Snackbar.make(view,"Error en el envio.",Snackbar.LENGTH_SHORT).show()}
                    }
                }
            }
        }
        fun setTitulo(x: String, rubroAux: String){
            val txt: TextView =view.findViewById(R.id.txtTitle)
            txt.setText(rubroAux+" - "+x)
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva_solicitada,parent,false)
        return (ReservaHolder(view))
    }

    override fun onBindViewHolder(holder: ReservaHolder, position: Int) {
        /*holder.getCard().setOnClickListener {
            onClick(position)
        }*/

        reservaList[position].url_download_image.let { holder.setAvatar(it) }
        reservaList[position].let { holder.edit(it) }
        reservaList[position].id_transaccion.let { holder.cancelar(it) }
        val aliasAux = reservaList[position].alias
        val rubroAux = reservaList[position].rubro_name
        if (aliasAux != null) {
            holder.setTitulo(aliasAux,rubroAux)
        }
    }

    override fun getItemCount(): Int {
        return reservaList.size
    }
}