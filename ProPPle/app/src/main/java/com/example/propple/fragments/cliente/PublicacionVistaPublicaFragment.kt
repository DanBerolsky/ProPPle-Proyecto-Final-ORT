package com.example.propple.adapters.cliente

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.api.publication.Comentario
import com.example.propple.databinding.FragmentPublicacionVistaPublicaBinding
import com.example.propple.fragments.cliente.PublicacionesFragmentArgs
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController
import com.example.propple.viewModel.cliente.PublicacionVistaPublicaViewModel
import com.ort.casodeusotest.adapters.ComentarioAdapter
import kotlin.properties.Delegates

class publicacionVistaPublicaFragment : Fragment() {

    companion object {
        fun newInstance() = publicacionVistaPublicaFragment()
    }

    private lateinit var v: View
    private lateinit var viewModel: PublicacionVistaPublicaViewModel
    private lateinit var binding: FragmentPublicacionVistaPublicaBinding
    private lateinit var adapter : ComentarioAdapter
    private lateinit var recycle : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.fragment_publicacion_vista_publica, container, false)
        binding=FragmentPublicacionVistaPublicaBinding.bind(v)
        //recycle = v.findViewById(R.id.recComentarios)
        return v
    }

    override fun onStart() {
        super.onStart()






    }
    fun setValoracion(cantEstrellas : Int){
        //val COLOR_AMARILLO : Color.argb =
        for ( i in 1..cantEstrellas){
            var estrellaAux = "estrella$i"
            Log.d("estre",estrellaAux)
            v.findViewWithTag<ImageView>(estrellaAux).setColorFilter(Color.argb(255, 245, 242, 66))
        }
    }
    fun nav( action: NavDirections){
        v.findNavController().navigate(action)
    }
    fun getWhatsapp(telefono:String,mensaje:String){
        //hola,%20qué%20tal?
        val oldValue = " "
        val newValue = "%20"
        val output = mensaje.replace(oldValue, newValue)
        Log.i("mensaje", output)
        val url = "https://api.whatsapp.com/send?phone=${telefono}&text=${output}";
        val i = Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i)
    }
    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var idPubli : Int = -1
        viewModel = ViewModelProvider(this).get(PublicacionVistaPublicaViewModel::class.java)
        var servicios = publicacionVistaPublicaFragmentArgs.fromBundle(requireArguments()).id
        viewModel.getPublication(servicios.toInt())
        viewModel.publi.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                idPubli=it.id_publicacion
                binding.textDescripciN.text=it.publicacion_description
                setValoracion(it.puntuacion)
                binding.textDireccion.text=it.location
                binding.textprecio.text="$"+it.precio_x_hora
                binding.textCantVeces.text="Ha realizado ${it.cant_transacciones} veces este servicio"
                binding.tTuloDelServicio.text=it.title
                binding.Nombre.text=it.user_name+" "+it.user_last_name
                imgController.getImgUrl(
                    it.url_download_image,
                    requireContext(),
                    binding.btnAvatar
                )


                binding.btnComen2.setOnClickListener {resutl ->
                    var act:NavDirections = publicacionVistaPublicaFragmentDirections.
                    actionPublicacionVistaPublicaFragmentToComentarios2Fragment2(it.id_publicacion.toLong())
                    nav(act)
                }
                binding.fabWapp1.setOnClickListener{resul->
                    val mensaje = "Hola ${it.user_name}!!/nContacto con usted para conocer mas sobre su ProppleService."
                    getWhatsapp(it.phone,mensaje)
                    //getWhatsapp("5491164960203","Hola que onda doc?")
                }
                binding.fabContactar.setOnClickListener{
                    if (idPubli!=-1){
                        val act = publicacionVistaPublicaFragmentDirections.actionPublicacionVistaPublicaFragmentToFormalizacionAcuerdoFragment2(idPubli)
                        nav(act)
                    }
                }
            }
        } )







    }

}