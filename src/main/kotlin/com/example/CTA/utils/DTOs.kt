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
    val id: Long,
    val price: Float,
    val make: String,
    val model: String,
    val dealerId: Long,
    val dealer: String,
    val image: String
)

data class DealerDTO(
    val id: Long,
    val name: String,
    val posts: List<PostResponseDTO>
)

data class CarDTO(
    val id: Long,
    val make: String,
    val model: String,
    val image: String?,
    val fuelType: String?,
    val doors: Int?,
    val transmission: String?,
    val horsepower: Int?,
    val year: Int?,
    val color: String?
)