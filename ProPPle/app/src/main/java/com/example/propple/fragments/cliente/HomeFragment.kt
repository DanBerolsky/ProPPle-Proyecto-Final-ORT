package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.propple.viewModel.cliente.HomeViewModel
import com.example.propple.R
import com.example.propple.databinding.FragmentCambiarContraseniaPrestadorBinding
import com.example.propple.databinding.HomeFragmentBinding

class homeFragment : Fragment() {

    companion object {
        fun newInstance() = homeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private lateinit var v:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.home_fragment, container, false)
        binding=HomeFragmentBinding.bind(v)
        return v
    }

    override fun onStart() {
        super.onStart()

        binding.btnPlomeria.setOnClickListener {
            val action = homeFragmentDirections.actionHomeFragmentToPublicacionesFragment("Plomeria")
            nav(action)
        }
        binding.btnDomestica.setOnClickListener {
            val action = homeFragmentDirections.actionHomeFragmentToPublicacionesFragment("Domestica")
            nav(action)
        }
        binding.btnElectricista.setOnClickListener {
            val action = homeFragmentDirections.actionHomeFragmentToPublicacionesFragment("Electricista")
            nav(action)
        }
        binding.btnAireAC.setOnClickListener {
            val action = homeFragmentDirections.actionHomeFragmentToPublicacionesFragment("acondicionado")
            nav(action)
        }
        binding.btnGasista.setOnClickListener {
            val action = homeFragmentDirections.actionHomeFragmentToPublicacionesFragment("Gasista")
            nav(action)
        }

    }

    fun nav(action:NavDirections){
        v.findNavController().navigate(action)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


    }

}