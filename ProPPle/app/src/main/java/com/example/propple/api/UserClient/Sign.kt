package com.example.propple.api.UserClient

data class Sign(
    val  id_usuario_cliente: String="0",
    val alias: String,
    val location: String?="0",
    val location_latitud: Double?=0.0,
    val location_longitud: Double?=0.0,
    val mail: String,
    val phone: String,
    val url_image: String?="0",
    val user_last_name: String,
    val user_name: String,
    val user_password: String,
    val date_of_birth : String,
    val gender: String,
    val visibility: Boolean?=true,
    val url_download_image:String
)
