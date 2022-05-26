package com.example.propple.api.interfaces

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Publication {
    @GET("/publication/getPublications/{token}/{precio}/{puntuacion}/{ubicacion}")
    suspend fun getPublications(@Path("token") token : String,
                                @Path("precio") precio : Boolean,
                                @Path("puntuacion") puntuacion : Boolean,
                                @Path("ubicacion") ubicacion : String,
                                @Path("rubro") rubro : String):Response<List<Publication>>

}


