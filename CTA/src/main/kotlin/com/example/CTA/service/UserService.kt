package com.example.CTA.service

import com.example.CTA.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserService : UserDetailsService {

    @Autowired
    lateinit var userRepository : UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findByUsernameField(username!!)
            .getOrNull()
            ?: throw UsernameNotFoundException("User not found with username: $username")
    }
}