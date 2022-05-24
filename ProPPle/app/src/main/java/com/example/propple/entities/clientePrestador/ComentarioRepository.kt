package com.ort.casodeusotest.entities

class ComentarioRepository {

    var comentarioList : MutableList<Comentario> = mutableListOf()

    init {
        comentarioList.add(Comentario("a", "a"))
        comentarioList.add(Comentario("a", "a"))
        comentarioList.add(Comentario("a", "a"))
        comentarioList.add(Comentario("a", "a"))
        comentarioList.add(Comentario("a", "a"))
    }

}