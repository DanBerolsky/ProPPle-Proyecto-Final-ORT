package com.example.propple.adapters.cliente

import android.annotation.SuppressLint
import android.graphics.Color
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
import com.example.propple.viewModel.cliente.PublicacionVistaPublicaViewModel
import com.ort.casodeusotest.adapters.ComentarioAdapter

class publicacionVistaPublicaFragment : Fragment() {

    companion object {
        fun newInstance() = publicacionVistaPublicaFragment()
    }

    private lateinit var v: View
    private lateinit var viewModel: PublicacionVistaPublicaViewModel
    private lateinit var binding: FragmentPublicacionVistaPublicaBinding
    lateinit var adapter : ComentarioAdapter
    lateinit var recycle : RecyclerView

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
    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicacionVistaPublicaViewModel::class.java)
        var servicios = publicacionVistaPublicaFragmentArgs.fromBundle(requireArguments()).id
        viewModel.getPublication(servicios.toInt())
        viewModel.publi.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                binding.textDescripciN.text=it.publicacion_description
                setValoracion(it.puntuacion)
                binding.textDireccion.text=it.location
                binding.textprecio.text="$"+it.precio_x_hora
                binding.textCantVeces.text="Ha realizado ${it.cant_transacciones} veces este servicio"
                binding.tTuloDelServicio.text=it.title

                binding.btnComen2.setOnClickListener {resutl ->
                    var act:NavDirections = publicacionVistaPublicaFragmentDirections.
                    actionPublicacionVistaPublicaFragmentToComentarios2Fragment2(it.id_publicacion.toLong())
                    nav(act)
                }

            }
        } )







    }

}