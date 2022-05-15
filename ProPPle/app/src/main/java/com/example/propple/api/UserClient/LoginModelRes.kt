package com.example.propple.api.UserClient

data class LoginModelRes(
    val jwt: Jwt,
    val rol: String
){

}

data class Jwt(
    val token: String
)
