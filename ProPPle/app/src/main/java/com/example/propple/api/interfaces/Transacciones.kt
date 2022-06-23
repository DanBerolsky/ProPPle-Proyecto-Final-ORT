package com.example.propple.api.interfaces

import com.example.propple.api.Transacciones.*
import com.example.propple.api.publication.Comentario
import retrofit2.Response
import retrofit2.http.*

interface Transacciones {

    @PUT("/transaccion/formalizarTransaccion")
    suspend fun formalizarTransaccion(@Body formalizarTransaccion:FormalizarTransaccion):Response<Void>

    @POST("/transaccion/crearTransaccion")
    suspend fun crearTransaccion(@Body x:CrearTransaccion):Response<Void>

    @POST("/transaccion/abonarTransaccion")
    suspend fun abonarTransaccion(@Body x:RechazarReserva):Response<MercadoPagoRes>

    @PUT("/transaccion/deleteTransaccionIniciada")
    suspend fun deleteTransaccionIniciada(@Body x: RechazarReserva):Response<Void>

    @POST("/transaccion/deleteTransaccionAbonada")
    suspend fun deleteTransaccionAbonada(@Body x: RechazarReserva):Response<Void>

    @PUT("/transaccion/deleteTransaccionPresupuestada")
    suspend fun deleteTransaccionPresupuestada(@Body x: RechazarReserva):Response<Void>

    @GET("transaccion/getCompras/{token}")
    suspend fun getCompras(@Path("token") token: String):Response<CompraCli>

    @GET("transaccion/getVentas/{token}")
    suspend fun getVentas(@Path("token") token: String):Response<VentasPro>

}