package com.example.propple.api.interfaces

import com.example.propple.api.Transacciones.CrearTransaccion
import com.example.propple.api.Transacciones.FormalizarTransaccion
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

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

}