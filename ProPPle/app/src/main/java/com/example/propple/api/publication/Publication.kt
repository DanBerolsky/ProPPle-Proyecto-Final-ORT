package com.example.propple.api.publication

data class Publication(
    val cant_transacciones: Int,
    val id_publicacion: Int,
    val id_rubro: Int,
    val id_usuario_prestador: Int,
    val location: String,
    val location_latitud: Double,
    val location_longitud: Double,
    val phone: String,
    val precio_x_hora: Int,
    val publicacion_description: String,
    val puntuacion: Int,
    val rubro_name: String,
    val title: String,
    val url_download_image: String,
    val user_last_name: String,
    val user_name: String,
    val visibility: Boolean,
    val show:Boolean?
)