package com.example.propple.utils

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts

object imgController {



    fun pickPhotoFromGalery(activity: Activity,code:Int) {
        val intent= Intent(Intent.ACTION_GET_CONTENT)
        intent.type="image/*"
        activity.startActivityForResult(intent,code)
    }
    //private fun saveImage(Co)

}