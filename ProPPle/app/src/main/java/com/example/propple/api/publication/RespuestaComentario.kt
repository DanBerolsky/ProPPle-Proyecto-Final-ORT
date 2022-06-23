package com.example.propple.api.publication

data class RespuestaComentario(
    val answer: String,
    val id_comentario: Int,
    val id_publicacion: Int,
    val token: String
)