package com.example.propple.utils

import android.graphics.Color
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern

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

        public fun esNumeroTelefonico(editTXT : EditText, viewTXT : TextView, txtDefaultColor : Int): Boolean {
            var numerico = false
            val REG = "^(?:(?:00)?549?)?0?(?:11|[2368]\\d)(?:(?=\\d{0,2}15)\\d{2})??\\d{8}\$"
            var PATTERN: Pattern = Pattern.compile(REG)
            fun CharSequence.isPhoneNumber() : Boolean = PATTERN.matcher(this).matches()
            if (editTXT.text.isPhoneNumber()) {
                viewTXT.setTextColor(txtDefaultColor)
                numerico = true
            } else {
                viewTXT.setTextColor(Color.RED)
            }
            return numerico
        }

        public fun noTieneNumerosNiSimbolos(editTXT : EditText, viewTXT : TextView, txtDefaultColor : Int): Boolean {
            var niNumerosNiSimbolos = false
            val REG = "^[a-zA-Z ]*\$"
            var PATTERN: Pattern = Pattern.compile(REG)
            fun CharSequence.hasNoNumerNoSymbol() : Boolean = PATTERN.matcher(this).matches()
            if (editTXT.text.hasNoNumerNoSymbol()) {
                viewTXT.setTextColor(txtDefaultColor)
                niNumerosNiSimbolos = true
            } else {
                viewTXT.setTextColor(Color.RED)
            }
            return niNumerosNiSimbolos
        }

        public fun noTieneSimbolos(editTXT : EditText, viewTXT : TextView, txtDefaultColor : Int): Boolean {
            var noSimbolos = false
            val REG = "^[a-zA-Z0-9 ]*\$"
            var PATTERN: Pattern = Pattern.compile(REG)
            fun CharSequence.hasNoSymbol() : Boolean = PATTERN.matcher(this).matches()
            if (editTXT.text.hasNoSymbol()) {
                viewTXT.setTextColor(txtDefaultColor)
                noSimbolos = true
            } else {
                viewTXT.setTextColor(Color.RED)
            }
            return noSimbolos
        }

        public fun esEmail(editTXT : EditText, viewTXT : TextView, txtDefaultColor : Int): Boolean {
            var campoEmail = false
            val REG = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+\$"
            var PATTERN: Pattern = Pattern.compile(REG)
            fun CharSequence.isEmail() : Boolean = PATTERN.matcher(this).matches()
            if (editTXT.text.isEmail()) {
                viewTXT.setTextColor(txtDefaultColor)
                campoEmail = true
            } else {
                viewTXT.setTextColor(Color.RED)
            }
            return campoEmail
        }

    }

}