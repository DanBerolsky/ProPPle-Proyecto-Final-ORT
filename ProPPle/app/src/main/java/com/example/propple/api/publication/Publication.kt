package com.example.propple.api.publication

data class Publication(
    val cant_transacciones: Int,
    val id_publicacion: Int,
    val id_rubro: Int,
    val id_usuario_prestador: String,
    val location: String,
    val location_latitud: Double,
    val location_longitud: Double,
    val precio_x_hora: Int,
    val publicacion_description: String,
    val puntuacion: Int,
    val title: String,
    val visibility: Boolean
)
