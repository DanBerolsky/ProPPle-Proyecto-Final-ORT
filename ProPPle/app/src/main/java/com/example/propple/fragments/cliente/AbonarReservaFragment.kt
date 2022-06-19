package com.example.propple.fragments.cliente

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.R
import com.example.propple.viewModel.cliente.AbonarReservaViewModel

class AbonarReservaFragment : Fragment() {

    companion object {
        fun newInstance() = AbonarReservaFragment()
    }

    private lateinit var viewModel: AbonarReservaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_abonar_reserva, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AbonarReservaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}