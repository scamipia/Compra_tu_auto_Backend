package com.example.CTA.utils

import org.springframework.context.annotation.Description

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

data class postPublishDTO(
    val price: Float,
    val carId: Long,
    val description: String
)

data class PostResponseDTO(
    val price: Float,
    val make: String,
    val model: String,
    val dealer: String,
    val image: String
)
