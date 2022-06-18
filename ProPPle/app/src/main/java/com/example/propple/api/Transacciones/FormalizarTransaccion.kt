package com.example.propple.api.Transacciones

data class FormalizarTransaccion(
    val date_of_work: String,
    val id_transaccion: Int,
    val location: String,
    val location_latitud: Double,
    val location_longitud: Double,
    val presupuesto: Int,
    val token: String
)