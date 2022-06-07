package com.example.propple.utils

import android.graphics.Color
import android.widget.EditText
import android.widget.TextView

class InputFieldValidator {

    companion object {
        public fun esCampoVacio(editTXT : EditText, viewTXT : TextView, txtDefaultColor : Int): Boolean {
            var vacio = false
            if(editTXT.text.isBlank()) {
                viewTXT.setTextColor(Color.RED)
                vacio = true
            } else {
                viewTXT.setTextColor(txtDefaultColor)
            }
            return vacio
        }
    }

}