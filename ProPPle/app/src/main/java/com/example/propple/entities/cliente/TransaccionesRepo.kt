package com.example.propple.entities.cliente

class TransaccionesRepo {
    var transaccionesList : MutableList<Transaccion> = mutableListOf<Transaccion>()
    init {
        transaccionesList.add(Transaccion())

        transaccionesList.add(Transaccion())

        transaccionesList.add(Transaccion())
    }
}