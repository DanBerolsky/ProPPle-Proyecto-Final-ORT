package com.example.propple.api.interfaces

import com.example.propple.api.Transacciones.CrearTransaccion
import com.example.propple.api.Transacciones.FormalizarTransaccion
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface Transacciones {

    @PUT("/transaccion/formalizarTransaccion")
    suspend fun formalizarTransaccion(@Body formalizarTransaccion:FormalizarTransaccion):Response<Void>

    @POST("/transaccion/crearTransaccion")
    suspend fun crearTransaccion(@Body x:CrearTransaccion):Response<Void>

}