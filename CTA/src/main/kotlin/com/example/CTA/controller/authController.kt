package com.example.CTA.controller

import com.example.CTA.service.AuthService
import com.example.CTA.utils.LoginDTO
import com.example.CTA.utils.RegisterDTO
import com.example.CTA.utils.UserBuilder
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @Autowired
    lateinit var authService: AuthService

    @PostMapping("/login")
    fun login(@RequestBody loginDTO: LoginDTO): ResponseEntity<String> {
        val result = authService.login(loginDTO)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/register")
    fun register(@RequestBody @Valid registerDto: RegisterDTO): ResponseEntity<String> {
        val user = UserBuilder()
            .withName(registerDto.name)
            .withUsername(registerDto.username)
            .withPassword(registerDto.password)
            .withRole(registerDto.role)
            .build()
        val result = authService.register(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/admin")
    fun testAdmin(): String {
        return "Hola admin"
    }

    @GetMapping("/user")
    fun testUser(): String {
        return "Hola user"
    }
}