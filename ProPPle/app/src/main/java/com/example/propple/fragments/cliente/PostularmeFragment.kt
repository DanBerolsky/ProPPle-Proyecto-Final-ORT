package com.example.propple.fragments.cliente

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.propple.R
import com.example.propple.databinding.PostularmeFragmentBinding
import com.example.propple.utils.InputFieldValidator
import com.example.propple.utils.fileController
import com.example.propple.utils.fileController.encodeFileToBase64Binary
import com.example.propple.utils.fileController.getFileName
import com.example.propple.utils.fileController.getRealPathFromURI
import com.example.propple.utils.imgController
import com.example.propple.viewModel.cliente.PostularmeViewModel
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.InputStream
import java.net.URI

class PostularmeFragment : Fragment() {


    private lateinit var viewModel: PostularmeViewModel
    private lateinit var v : View
    private lateinit var btnPostularme : Button
    private lateinit var binding:PostularmeFragmentBinding
    private var rubro:String=""
    private var img64:String=""
    val imageLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        var uri = it.data?.data!!
        binding.InCv.setText(getFileName(uri,requireContext()))
        img64= imgController.base64Encode(uri, requireContext())
        //val sss = encodeFileToBase64Binary(File(uri.path))
        //Snackbar.make(v,sss.toString(),Snackbar.LENGTH_LONG).show()
        //var adsa : InputStream? = activity?.contentResolver?.openInputStream(uri)
        //Snackbar.make(v,adsa.toString() ,Snackbar.LENGTH_LONG).show()
        //var dfPath = uri.path
        //var file: File = File(dfPath)
        //val encode = fileController.encodeFileToBase64Binary(file)
        //var absolutePathPdf = file.getAbsolutePath()
    }

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

        binding.btnAdjt.setOnClickListener {
           // fileController.pickFile(fileLaucher)
            imgController.pickPhotoFromGalery(imageLauncher)
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
                    rubro = resources.getStringArray(R.array.rubros)[position]
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
        btnPostularme.setOnClickListener {
            if(verificarCamposVacios()) {
                Snackbar.make(v, "Los campos con * son obligatorios", Snackbar.LENGTH_SHORT).show()
            } else {
                if (img64!="")
                    viewModel.postPostulacion(binding.InRubro.text.toString(),img64)
                else
                    Snackbar.make(v,"Error: imagen :(",Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it=="Enviado!"){
                Snackbar.make(v,"Listo!! Postulacion enviada. ",Snackbar.LENGTH_SHORT).show()
                val action = PostularmeFragmentDirections.actionPostularmeFragment2ToPostularme2Fragment2()
                v.findNavController().navigate(action)
            }
            else{
                Snackbar.make(v,"Error",Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun verificarCamposVacios(): Boolean {
        var campoVacio = false
        var txvDefaultColor = 1979711488
        if (InputFieldValidator.esCampoVacio(binding.InRubro, binding.txvInRubroP, txvDefaultColor) && !campoVacio) campoVacio = true
        if (InputFieldValidator.esCampoVacio(binding.InCv, binding.txvCVP, txvDefaultColor) && !campoVacio) campoVacio = true
        return campoVacio
    }

}