package com.example.CTA.service

import com.example.CTA.model.User
import com.example.CTA.repository.UserRepository
import com.example.CTA.utils.LoginDTO
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    lateinit var jwtService: JwtService
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Transactional
    fun register(user: User): String {
        user.passwordField = passwordEncoder.encode(user.password)
        userRepository.save(user)
        return jwtService.generateToken(user)
    }

    fun login(loginDTO: LoginDTO): String {
        try{
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginDTO.username,
                    loginDTO.password
                )
            )
        }catch (e: Exception){
            throw IllegalArgumentException("Credenciales inválidas")
        }
        val user: User = userRepository.findByUsernameField(loginDTO.username).get()
        return jwtService.generateToken(user)
    }
}