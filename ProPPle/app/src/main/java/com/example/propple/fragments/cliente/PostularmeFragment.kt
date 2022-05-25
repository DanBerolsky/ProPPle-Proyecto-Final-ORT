package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.propple.viewModel.cliente.PostularmeViewModel
import com.example.propple.R

class PostularmeFragment : Fragment() {



    private lateinit var viewModel: PostularmeViewModel
    private lateinit var v : View
    private lateinit var btnPostularme : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.postularme_fragment, container, false)
        btnPostularme = v.findViewById(R.id.btnEnviarCv)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnPostularme.setOnClickListener {
            //val action = PostularmeFragmentDirections.actionPostularmeFragment2ToPostularme2Fragment2()
            //v.findNavController().navigate(action)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostularmeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}