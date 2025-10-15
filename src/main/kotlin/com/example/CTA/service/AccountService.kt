package com.example.CTA.service

import com.example.CTA.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class AccountService : UserDetailsService {

    @Autowired
    lateinit var accountRepository : AccountRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        return accountRepository.findByUsernameField(username!!)
            .getOrNull()
            ?: throw UsernameNotFoundException("User not found with username: $username")
    }

}