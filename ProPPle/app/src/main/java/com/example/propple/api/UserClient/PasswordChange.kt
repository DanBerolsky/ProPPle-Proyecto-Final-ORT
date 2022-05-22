package com.example.propple.api.UserClient

data class PasswordChange(
    val new_user_password: String,
    val token: String,
    val user_password: String
)