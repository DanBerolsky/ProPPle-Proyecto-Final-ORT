package com.example.propple.api.interfaces

import com.example.propple.api.UserClient.LoginModel
import com.example.propple.api.UserClient.LoginModelRes
import com.example.propple.api.UserClient.Sign
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginSignInService {
    @POST("/login")
    suspend fun login(@Body loginModel : LoginModel):Response<LoginModelRes>

    @POST("/signup")
    suspend fun sign(@Body newUser : Sign): Response<Void>
}