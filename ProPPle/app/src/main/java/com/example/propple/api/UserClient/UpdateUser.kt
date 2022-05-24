package com.example.propple.api.UserClient

data class UpdateUser(
    val token :String,
    val alias: String,
    val date_of_birth: String,
    val gender: String,
    val location: String,
    val location_latitud: Double,
    val location_longitud: Double,
    val phone: String,
    val url_image: String,
    val user_last_name: String,
    val user_name: String
)