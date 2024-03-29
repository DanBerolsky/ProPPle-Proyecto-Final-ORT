package com.example.propple.fragments.clientePrestador

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.propple.R
import com.example.propple.adapters.cliente.Comentario2Adapter
import com.google.android.material.snackbar.Snackbar
import com.ort.casodeusotest.adapters.ComentarioAdapter
import com.ort.casodeusotest.viewModel.ComentariosViewModel

class ComentariosFragment : Fragment() {

    companion object {
        fun newInstance() = ComentariosFragment()
    }

    private lateinit var viewModel: ComentariosViewModel
    private lateinit var v : View
    lateinit var recyclerComentarios : RecyclerView
    lateinit var adapter : ComentarioAdapter
    //var repository : ComentarioRepository = ComentarioRepository()
    private lateinit var fabVolverPublicacion2 : FloatingActionButton
    private lateinit var fabResponder : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.comentarios_fragment, container, false)
        recyclerComentarios = v.findViewById(R.id.recComentarios)
        //fabVolverPublicacion2 = v.findViewById(R.id.fabVolverPublicacion2)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        //fabVolverPublicacion2.setOnClickListener {
          //  val action = ComentariosFragmentDirections.actionComentariosFragmentToPublicacionFragment(ComentariosFragmentArgs.fromBundle(requireArguments()).rubroPosition)
            //v.findNavController().navigate(action)
        //}
        recyclerComentarios.setHasFixedSize(true)
        recyclerComentarios.layoutManager = LinearLayoutManager(context)
        /*adapter = ComentarioAdapter(repository.comentarioList) {
            fabResponder = v.findViewById(R.id.fabResponder)
            fabResponder.setOnClickListener{
                Snackbar.make(v, "Respondiste el comentario", Snackbar.LENGTH_SHORT).show()
            }
        }
        recyclerComentarios.adapter = adapter
    */}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ComentariosViewModel::class.java)
        val id = ComentariosFragmentArgs.fromBundle(requireArguments()).id
        viewModel.getComentarios(id)
        //binding.btnEnviar.setOnClickListener{
          //  viewModel.newComment(servicios.toInt(),binding.inPregunta.text.toString())
        //}

        viewModel.comentarios.observe(viewLifecycleOwner, Observer {
            var it2=it
            if (it != null) {
                it2=it.filter { it.answer==null }
            }
            if (it2?.size==0){
                v.findViewById<CardView>(R.id.vacio).visibility=View.VISIBLE
            }
            adapter = it2?.let { it1 -> ComentarioAdapter(it1) }!!
            recyclerComentarios.adapter=adapter
        } )
    }

}