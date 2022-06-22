package com.example.propple.api.Transacciones

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transaccion(
    val alias: String,
    val fecha: String?,
    val id_estado: Int?,
    val id_publicacion: Int,
    val id_transaccion: Int,
    val id_usuario_cliente: String,
    val id_usuario_prestador: Int,
    val id_valuacion: Int,
    val location: String,
    val location_latitud: Int,
    val location_longitud: Int,
    val presupuesto: Double?,
    val url_download_image: String,
    val rubro_name:String,
    val phone: String
): Parcelable