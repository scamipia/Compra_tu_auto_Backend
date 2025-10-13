package com.example.CTA.utils

data class LoginDTO(
    val username: String,
    val password: String
)

data class RegisterDTO(
    val name: String,
    val username: String,
    val password: String,
    val role: String
)

data class AuthResponse(
    val username: String,
    val token: String,
    val role: String
)

