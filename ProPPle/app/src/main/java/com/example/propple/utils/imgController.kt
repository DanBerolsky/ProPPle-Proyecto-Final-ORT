package com.example.propple.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import java.io.ByteArrayOutputStream
import java.io.IOException

object imgController {



    fun pickPhotoFromGalery(imageLauncher: ActivityResultLauncher<Intent>) {
        val intent= Intent(Intent.ACTION_GET_CONTENT)
        intent.type="image/*"
        imageLauncher.launch(intent)
    }
    fun base64decode(encodedImage:String): Bitmap {
        val decodedString: ByteArray = Base64.decode(encodedImage, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        return decodedByte
    }

    fun base64Encode(uri: Uri,context:Context):String{
        var sImage=""
        //val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri)
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri)
            // initialize byte stream
            val stream = ByteArrayOutputStream()
            // compress Bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            // Initialize byte array
            val bytes: ByteArray = stream.toByteArray()
            // get base64 encoded string
            sImage = Base64.encodeToString(bytes, Base64.DEFAULT)

            // set encoded text on textview
            //textView.setText(sImage)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return sImage
    }
    //private fun saveImage(Co)

    fun getImgUrl(url: String,context: Context,imgView:ImageView) {
        if (url.isNotEmpty()) {
            Glide.with(context).load(url).into(imgView)
        }
    }
}