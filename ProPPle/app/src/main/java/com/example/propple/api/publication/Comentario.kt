package com.example.propple.api.publication

data class Comentario(
    val content: String,
    val id_comentario: Int,
    val id_publicacion: Int,
    val id_usuario_cliente: String,
    val visibility: Boolean
)