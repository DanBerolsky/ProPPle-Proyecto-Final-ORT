package com.example.propple.api.interfaces

import com.example.propple.api.UserClient.PasswordChange
import com.example.propple.api.UserClient.Sign
import com.example.propple.api.UserClient.UpdateUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserClientService {
    @GET("/user/getOne/{token}")
    suspend fun getOne(@Path("token") token : String):Response<Sign>

    @PUT("/user/passwordChange")
    suspend fun passwordChange(@Body x : PasswordChange):Response<Void>

    @PUT("/user/updateUser")
    suspend fun updateUser(@Body x:UpdateUser):Response<Void>
/*
    @Multipart
    @POST
    fun getEVERYTHING2(
        @Part image: MultipartBody.Part,
        @Part("example") myExample: String
    ): Call<*>*/

//    val requestBody =   RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
//    val a = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
}