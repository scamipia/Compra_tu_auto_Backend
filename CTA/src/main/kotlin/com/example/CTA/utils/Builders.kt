package com.example.CTA.utils

import com.example.CTA.model.User

class UserBuilder {

    private var id: Long? = null
    private var name: String? = null
    private var username: String? = null
    private var password: String? = null
    private var role: String? = null

    fun build(): User{
        val user = User()
        user.id = id
        user.name = name
        user.passwordField = password
        user.usernameField = username
        user.role = role
        return user
    }

    fun withId(id: Long) = apply { this.id = id }
    fun withRole(role: String) = apply { this.role = role }
    fun withName(name: String) = apply { this.name = name }
    fun withUsername(username: String) = apply{this.username = username}
    fun withPassword(password: String) = apply{this.password = password}
}