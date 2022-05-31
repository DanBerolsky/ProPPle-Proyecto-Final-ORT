package com.example.propple.fragments.cliente

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.PostularmeFragmentBinding
import com.example.propple.viewModel.cliente.PostularmeViewModel
import com.google.android.material.snackbar.Snackbar

class PostularmeFragment : Fragment() {



    private lateinit var viewModel: PostularmeViewModel
    private lateinit var v : View
    private lateinit var btnPostularme : Button
    private lateinit var binding:PostularmeFragmentBinding
    private var rubro:String=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.postularme_fragment, container, false)
        btnPostularme = v.findViewById(R.id.btnEnviarCv)
        binding=PostularmeFragmentBinding.bind(v)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnPostularme.setOnClickListener {
            val action = PostularmeFragmentDirections.actionPostularmeFragment2ToPostularme2Fragment2()
            v.findNavController().navigate(action)
        }

        binding.imgRubro.setOnClickListener {
            binding.spinner3.performClick()
            binding.spinner3.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    rubro = getResources().getStringArray(R.array.rubros)[position]
                    Snackbar.make(v,rubro, Snackbar.LENGTH_SHORT).show()
                    binding.InRubro.setText(rubro)
                }
            }
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostularmeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}