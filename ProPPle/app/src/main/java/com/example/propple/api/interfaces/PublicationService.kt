package com.example.propple.api.interfaces

import com.example.propple.api.UserClient.Sign
import com.example.propple.api.publication.*
import retrofit2.Response
import retrofit2.http.*

interface PublicationService {
    @GET("publication/getPublications/{token}/{precio}/{puntuacion}/{ubicacion}/{rubro}")
    suspend fun getPublications(@Path("token") token : String,
                                @Path("precio") precio : Boolean,
                                @Path("puntuacion") puntuacion : Boolean,
                                @Path("ubicacion") ubicacion : String,
                                @Path("rubro") rubro : String):Response<List<Publication>>
    @GET("publication/getPublication/{token}/{id}")
    suspend fun getPublication(@Path("token") token: String, @Path("id") id: Int):Response<Publication>



    @GET("publication/getComentario/{token}/{id}")
    suspend fun getPublicationCometarios(@Path("token") token: String, @Path("id") id: Int):Response<List<Comentario>>

    @POST("publication/postComentario")
    suspend fun nuevoComentario(@Body x : ComentarioNuevo):Response<Void>

    @POST("publication/postPostulacion")
    suspend fun postPostulacion(@Body x : CrearPostulacion):Response<Void>

    @GET("publication/getPublicationForPrestador/{token}/{id}")
    suspend fun getPublicationForPrestador(@Path("token") token: String, @Path("id") id: Int):Response<Publication>

    @GET("publication/getPublicationsForPrestador/{token}")
    suspend fun getPublicationsForPrestador(@Path("token") token: String):Response<List<PublicationCuenta>>

    @POST("publication/postRespuesta")
    suspend fun postRespuesta(@Body x : RespuestaComentario):Response<Void>

    @PUT("publication/changeVisibility")
    suspend fun changeVisibility(@Body x : ChangeVisibility):Response<Void>

    @PUT("publication/updatePublication")
    suspend fun updatePublication(@Body x : UpdatePublication):Response<Void>

    @POST("/denuncia")
    suspend fun denuncia(@Body x : Denuncia):Response<Void>

}


