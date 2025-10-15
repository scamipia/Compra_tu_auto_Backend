package com.example.CTA.repository

import com.example.CTA.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AccountRepository : JpaRepository<Account, Long> {

    fun findByUsernameField(string: String): Optional<Account>
}