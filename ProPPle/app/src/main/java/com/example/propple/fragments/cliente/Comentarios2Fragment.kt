package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propple.R
import com.example.propple.adapters.cliente.Comentario2Adapter
import com.example.propple.databinding.FragmentComentarios2Binding
import com.example.propple.shared.ProPPle
import com.example.propple.shared.ProPPle.Companion.prefs
import com.example.propple.utils.imgController
import com.example.propple.viewModel.cliente.Comentarios2ViewModel
import com.example.propple.viewModel.cliente.PublicacionVistaPublicaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.adapters.ComentarioAdapter


class Comentarios2Fragment : Fragment() {

    companion object {
        fun newInstance() = Comentarios2Fragment()
    }

    private lateinit var viewModel: PublicacionVistaPublicaViewModel
    private lateinit var v : View
    lateinit var recyclerComentarios2 : RecyclerView
    lateinit var adapter : Comentario2Adapter
    //var repository : ComentarioRepository = ComentarioRepository()
    private lateinit var fabConsultar : FloatingActionButton
    private lateinit var binding : FragmentComentarios2Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_comentarios2, container, false)
        recyclerComentarios2 = v.findViewById(R.id.recComentarios2)
        binding=FragmentComentarios2Binding.bind(v)
        return v
    }

    override fun onStart() {
        super.onStart()
        recyclerComentarios2.setHasFixedSize(true)
        recyclerComentarios2.layoutManager = LinearLayoutManager(context)

        if (ProPPle.prefs.getUrlImageString()!="")
            imgController.getImgUrl(
                prefs.getUrlImageString(),
                requireContext(),
                v.findViewById<ImageView>(R.id.imgCliente))

        /*adapter = Comentario2Adapter(repository.comentarioList) {
            fabConsultar = v.findViewById(R.id.fabConsultar)
            fabConsultar.setOnClickListener{
                Snackbar.make(v, "Hiciste una consulta", Snackbar.LENGTH_SHORT).show()
            }
        }
        recyclerComentarios2.adapter = adapter*/


    }

    fun cargarListaDeComentarios(servicios:Long){
        viewModel.getComentarios(servicios.toInt())
        viewModel.comentarios.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                adapter = Comentario2Adapter(it)
                recyclerComentarios2.adapter=adapter
            }

        })
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PublicacionVistaPublicaViewModel::class.java)
        var servicios = Comentarios2FragmentArgs.fromBundle(requireArguments()).id
        Log.i("id",servicios.toString())
        cargarListaDeComentarios(servicios)
        binding.btnEnviar.setOnClickListener{
            viewModel.newComment(servicios.toInt(),binding.inPregunta.text.toString())
        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it=="1")
            {
                Snackbar.make(v,"Enviado",Snackbar.LENGTH_SHORT).show()
                v.findViewById<EditText>(R.id.inPregunta).setText("")
                cargarListaDeComentarios(servicios)
            }else if (it=="2")
            {
                Snackbar.make(v,"Error, intente mas tarde.",Snackbar.LENGTH_SHORT).show()
            }else if (it=="3"){
                Snackbar.make(v,"mensaje vacio.",Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(v,"ERROR",Snackbar.LENGTH_SHORT).show()
            }

        } )

    }

}