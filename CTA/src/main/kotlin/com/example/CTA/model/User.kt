package com.example.CTA.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class User : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id:Long? = null

    @Column(nullable = false)
    var name:String? = null
    @Column(name="username", nullable = false, unique = true)
    var usernameField:String? = null
    @Column(name="password")
    var passwordField:String? = null
    @Column
    var role: String? = null

    override fun getAuthorities(): Collection<GrantedAuthority?>? = mutableListOf(SimpleGrantedAuthority(role))

    override fun getPassword(): String? = passwordField

    override fun getUsername(): String? = usernameField
}