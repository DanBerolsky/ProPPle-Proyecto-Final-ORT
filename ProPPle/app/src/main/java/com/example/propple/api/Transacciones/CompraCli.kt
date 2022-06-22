package com.example.propple.api.Transacciones

data class CompraCli(
    val cancelados: List<Transaccion>,
    val comentados: List<Transaccion>,
    val finalizados: List<Transaccion>,
    val pendientes: List<Transaccion>,
    val proximos: List<Transaccion>
)

