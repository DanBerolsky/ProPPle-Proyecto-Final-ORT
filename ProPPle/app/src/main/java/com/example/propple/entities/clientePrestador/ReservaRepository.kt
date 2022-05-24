package com.ort.casodeusotest.entities

class ReservaRepository {

    var reservaList : MutableList<Reserva> = mutableListOf()

    init {
        reservaList.add(Reserva("Laura", "Plomería"))
        //reservaList.add(Reserva("Fernando", "Electricidad"))
        //reservaList.add(Reserva("Mabel", "Gas"))
        //reservaList.add(Reserva("Gonzalo", "Doméstica"))
        //reservaList.add(Reserva("Juana", "Aire Acondicionado"))
    }

}