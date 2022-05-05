package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.propple.viewModel.Postularme2ViewModel
import com.example.propple.R

class Postularme2Fragment : Fragment() {

    companion object {
        fun newInstance() = Postularme2Fragment()
    }

    private lateinit var viewModel: Postularme2ViewModel
    private lateinit var v : View
    private lateinit var btnRegresar : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v= inflater.inflate(R.layout.postularme2_fragment, container, false)
        btnRegresar=v.findViewById(R.id.btnRegresar)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnRegresar.setOnClickListener {
            val action = Postularme2FragmentDirections.actionPostularme2FragmentToCuentaFragment()
            v.findNavController().navigate(action)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Postularme2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}