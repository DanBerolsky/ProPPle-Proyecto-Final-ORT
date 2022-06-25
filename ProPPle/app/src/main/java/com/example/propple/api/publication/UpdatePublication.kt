package com.example.propple.api.publication

data class UpdatePublication(
    val location: String,
    val location_latitud: Double,
    val location_longitud: Double,
    val precio_x_hora: Int,
    val publicacion_description: String,
    val title: String,
    val token: String,
    val id_publicacion:Int
)