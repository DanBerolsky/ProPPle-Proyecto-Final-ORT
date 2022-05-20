package com.example.propple.api.interfaces

import com.example.propple.api.UserClient.Sign

import retrofit2.Response
import retrofit2.http.*

interface UserClientService {
    @GET("/user/getOne/{token}")
    suspend fun getOne(@Path("token") token : String):Response<Sign>
}