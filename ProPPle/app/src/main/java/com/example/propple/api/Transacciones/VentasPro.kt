package com.example.propple.api.Transacciones

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VentasPro(
    val cancelados: List<Transaccion>,
    val comentados: List<Transaccion>,
    val finalizados: List<Transaccion>,
    val inicial: List<Transaccion>,
    val pendientes: List<Transaccion>,
    val proximos: List<Transaccion>
): Parcelable
