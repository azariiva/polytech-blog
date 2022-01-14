package me.freedom4live.ktor.service

import io.ktor.auth.*
import me.freedom4live.ktor.db.entity.UserCredentials
import me.freedom4live.ktor.db.repository.UserCredentialsExposedRepository

object AuthProvider {
    fun tryAuth(username: String, password: String): UserIdPrincipal? {
        return UserCredentialsExposedRepository.find(username)?.let { user ->
            when (password) {
                user.password -> UserIdPrincipal(user.username)
                else -> null
            }
        }
    }

    fun tryRegister(username: String, password: String): UserIdPrincipal? {
        UserCredentialsExposedRepository.find(username)?.let { return null }
        UserCredentialsExposedRepository.add(UserCredentials(username = username, password = password))
        return UserIdPrincipal(username)
    }
}