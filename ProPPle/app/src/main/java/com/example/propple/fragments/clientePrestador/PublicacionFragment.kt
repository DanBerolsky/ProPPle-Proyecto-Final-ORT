package com.ort.casodeusotest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.PublicacionFragmentBinding
import com.example.propple.shared.ProPPle
import com.example.propple.utils.imgController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.viewModel.PublicacionViewModel

class PublicacionFragment : Fragment() {

    companion object {
        fun newInstance() = PublicacionFragment()
    }

    private lateinit var v : View
    private lateinit var btnEditarPublicacion : FloatingActionButton
    private lateinit var btnComentarios : Button
    private lateinit var fabVolverMisPublic : FloatingActionButton
    private lateinit var binding:PublicacionFragmentBinding
    private lateinit var viewModel: PublicacionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.publicacion_fragment, container, false)
        btnEditarPublicacion = v.findViewById(R.id.btnEditarPublicacion)
        btnComentarios = v.findViewById(R.id.btnComentarios)
        binding=PublicacionFragmentBinding.bind(v)
        //fabVolverMisPublic = v.findViewById(R.id.fabVolverMisPublic)
        return v
    }

    override fun onStart() {
        super.onStart()
        btnEditarPublicacion.setOnClickListener {
            val action = PublicacionFragmentDirections.actionPublicacionFragmentToPublicarServicioFragment(PublicacionFragmentArgs.fromBundle(requireArguments()).id)
            v.findNavController().navigate(action)
        }
        btnComentarios.setOnClickListener {
            val action = PublicacionFragmentDirections.actionPublicacionFragmentToComentariosFragment(PublicacionFragmentArgs.fromBundle(requireArguments()).id)
            v.findNavController().navigate(action)
        }
        /*fabVolverMisPublic.setOnClickListener {
            val action = PublicacionFragmentDirections.actionPublicacionFragmentToMisPublicacionesFragment()
            v.findNavController().navigate(action)
        }*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicacionViewModel::class.java)
        val id = PublicacionFragmentArgs.fromBundle(requireArguments()).id
        viewModel.getPublicationForPrestador(id)
        viewModel.publi.observe(viewLifecycleOwner, Observer {
            binding.textCantVeces.text="Ha realizado ${it.cant_transacciones} veces este servicio"
            binding.textView16.text=it.user_name
            binding.textprecio.text="$"+it.precio_x_hora
            binding.textDireccion.text = it.location
            binding.textDescripciN.text=it.publicacion_description
            binding.tTuloDelServicio.text=it.title
            setEstado(it.visibility)
            binding.button10.text=it.puntuacion.toString()
            setAvatar(it.url_download_image)
            it.show?.let { it1 -> estadoOcultar(it1) }
        })

        viewModel.status2.observe(viewLifecycleOwner, Observer {
            Snackbar.make(v,"ERROR",Snackbar.LENGTH_SHORT).show()
        })
        binding.switch3.setOnCheckedChangeListener{buttonView, isChecked ->
            viewModel.changeVisibility(id)
        }

    }
    fun setEstado(visibility: Boolean) {
        binding.switch3.isChecked = visibility
    }
    fun setAvatar(img64: String) {
        if (ProPPle.prefs.getUrlImageString() != "")
            imgController.getImgUrl(
                img64,
                v.context,
                v.findViewById<ImageView>(R.id.imageView7)
            )
    }
    fun estadoOcultar(mostrar:Boolean){
        if (!mostrar){
           binding.cardVisible.visibility = View.GONE
        }
    }
}