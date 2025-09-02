package com.example.CTA.repository

import com.example.CTA.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

    fun findByUsernameField(string: String): Optional<User>
}