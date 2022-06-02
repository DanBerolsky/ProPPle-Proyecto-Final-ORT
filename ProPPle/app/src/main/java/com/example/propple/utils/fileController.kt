package com.example.propple.utils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Base64
import androidx.activity.result.ActivityResultLauncher
import java.io.*


object fileController {

    fun pickFile(fileLaucher: ActivityResultLauncher<Intent>) {
        val intent= Intent(Intent.ACTION_GET_CONTENT)
        //intent.type = "application/msword"
        intent.type = "application/pdf"
        //intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        fileLaucher.launch(intent)
    }



    @SuppressLint("Range")
    fun getFileName(uri: Uri, context: Context):String{
        var res = ""
        if (uri.scheme.equals("content")){
            var cursor = context.contentResolver.query(uri,null,null,null,null)
            try {

                if (cursor!=null && cursor.moveToFirst()){
                    res=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                cursor?.close()
            }
            if (res==null){
                res=uri.path.toString()
                val cut = res.lastIndexOf('/')
                if (cut!=-1)
                    res=res.substring(cut+1)

            }


        }
        return res
    }



    @Throws(IOException::class)
    private fun encodeFileToBase64Binary(uri: Uri, context: Context): String? {

        //val pdfFilePath: Path = Paths.get("/file/path/your_file.pdf")

        // Read file to byte array

        // Read file to byte array
        //val pdfByteArray: ByteArray = Files.readAllBytes(pdfFilePath)



       // val value = Base64.encodeToString(bytes, Base64.DEFAULT)
        //return value
        return ""
    }
// Write byte array to file

    // Write byte array to file
    //Files.write(pdfFilePath, pdfByteArray)


    fun getRealPathFromURI(context: Context, contentUri: Uri?): String? {
        var cursor: Cursor? = null
        return try {
            val proj = arrayOf(MediaStore.Video.Media.DATA)
            cursor = context.contentResolver.query(contentUri!!, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } finally {
            cursor?.close()
        }
    }


    fun encodeFileToBase64Binary(yourFile: File): String? {
        val size = yourFile.length().toInt()
        val bytes = ByteArray(size)
        try {
            val buf = BufferedInputStream(FileInputStream(yourFile))
            buf.read(bytes, 0, bytes.size)
            buf.close()
        } catch (e: FileNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return Base64.encodeToString(bytes, Base64.NO_WRAP)
    }




}