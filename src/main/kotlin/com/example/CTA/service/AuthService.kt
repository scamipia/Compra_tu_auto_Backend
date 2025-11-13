package com.example.CTA.service

import com.example.CTA.model.Account
import com.example.CTA.repository.AccountRepository
import com.example.CTA.utils.AuthResponse
import com.example.CTA.utils.LoginDTO
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService {

    @Autowired
    lateinit var accountRepository: AccountRepository
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    lateinit var jwtService: JwtService
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Transactional
    fun register(account: Account): AuthResponse {
        account.passwordField = passwordEncoder.encode(account.password)
        accountRepository.save(account)
        val token = jwtService.generateToken(account)
        return AuthResponse(
            account.username.toString(),
            token,
            account.role.toString()
        )
    }

    fun registerAll(users: List<Account>) {
        users.forEach{ acc ->
            acc.passwordField = passwordEncoder.encode(acc.passwordField)
            accountRepository.save(acc)
        }
    }

    fun login(loginDTO: LoginDTO): AuthResponse {
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
        val account: Account = accountRepository.findByUsernameField(loginDTO.username).get()
        val token = jwtService.generateToken(account)
        return AuthResponse(
            account.username.toString(),
            token,
            account.role.toString()
        )
    }
}