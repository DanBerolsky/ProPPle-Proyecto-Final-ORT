package com.example.propple.api.publication

data class Comentario(
    val answer: String?,
    val content: String,
    val date_of_creation: String,
    val foto_cliente: String,
    val foto_prestador: String,
    val id_comentario: Int,
    val id_publicacion: Int,
    val id_usuario_cliente: String,
    val visibility: Boolean
)