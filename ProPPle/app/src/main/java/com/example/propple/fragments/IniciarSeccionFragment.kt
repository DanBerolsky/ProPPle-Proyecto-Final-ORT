package com.example.propple.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.propple.viewModel.IniciarSeccionViewModel
import com.example.propple.R

class IniciarSeccionFragment : Fragment() {

    companion object {
        fun newInstance() = IniciarSeccionFragment()
    }

    private lateinit var viewModel: IniciarSeccionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IniciarSeccionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}