package com.example.propple.entities.cliente

class publicacionesRepo {
    var publicacionesList : MutableList<Publicacion> = mutableListOf<Publicacion>()
    init {
        publicacionesList.add(Publicacion(valoracion = 3))

        publicacionesList.add(Publicacion(valoracion = 1))

        publicacionesList.add(Publicacion(valoracion = 2))

        publicacionesList.add(Publicacion())

        publicacionesList.add(Publicacion())

        publicacionesList.add(Publicacion())
        publicacionesList.add(Publicacion())
        publicacionesList.add(Publicacion())
        publicacionesList.add(Publicacion())
        publicacionesList.add(Publicacion())
    }
}



