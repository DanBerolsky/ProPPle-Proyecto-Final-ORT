package com.example.propple.api.interfaces

import com.example.propple.api.Transacciones.CompraCli
import com.example.propple.api.Transacciones.CrearTransaccion
import com.example.propple.api.Transacciones.FormalizarTransaccion
import com.example.propple.api.Transacciones.VentasPro
import com.example.propple.api.publication.Comentario
import retrofit2.Response
import retrofit2.http.*

interface Transacciones {

    @PUT("/transaccion/formalizarTransaccion")
    suspend fun formalizarTransaccion(@Body formalizarTransaccion:FormalizarTransaccion):Response<Void>

    @POST("/transaccion/crearTransaccion")
    suspend fun crearTransaccion(@Body x:CrearTransaccion):Response<Void>

    /*@POST("/transaccion/abonarTransaccion")
    suspend fun abonarTransaccion(@Body ):Response<Void>

    @GET("/transaccion/success")
    suspend fun success(@Body ):Response<Void>

    @GET("/transaccion/failure")
    suspend fun failure(@Body ):Response<Void>

    @GET("/transaccion/pending")
    suspend fun pending(@Body ):Response<Void>

    @PUT("/transaccion/deleteTransaccionPresupuestada")
    suspend fun deleteTransaccionPresupuestada(@Body ):Response<Void>*/

    @GET("transaccion/getCompras/{token}")
    suspend fun getCompras(@Path("token") token: String):Response<CompraCli>

    @GET("transaccion/getVentas/{token}")
    suspend fun getVentas(@Path("token") token: String):Response<VentasPro>

}