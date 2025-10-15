package com.example.CTA.dataInit

import com.example.CTA.service.AuthService
import com.example.CTA.utils.AccountBuilder
import com.example.CTA.utils.CustomerBuilder
import com.example.CTA.utils.DealerBuilder
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order

@Configuration
class AccountDataLoader(private val authService: AuthService) {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun initUsers()= CommandLineRunner {
        val customer = CustomerBuilder()
            .name("customer1")
            .username("customer@test")
            .password("customer")
            .build()
        val dealer = DealerBuilder()
            .name("dealer1")
            .username("dealer@test")
            .password("dealer")
            .build()
        val admin = AccountBuilder()
            .name("admin")
            .username("admin@test")
            .password("admin")
            .role("ADMIN")
            .build()
        authService.registerAll(listOf(customer, dealer, admin))

    }
}