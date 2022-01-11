package me.freedom4live.ktor.plugins

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.sessions.*
import me.freedom4live.ktor.Cookies

fun Application.sessions() {
    install(Sessions) { //Enable Sessions
        cookie<UserIdPrincipal>( //Use UserIdPrincipal as user data principal container, and identifying it via cookies
            Cookies.AUTH_COOKIE, // Just String constant with value: const val AUTH_COOKIE = "auth"
            storage = SessionStorageMemory() // We use default in-memory storage, but you can write your own realization and store sessions wherever you wish
        ) {
            cookie.path = "/"  // We cookies should work
            cookie.extensions["SameSite"] = "lax"
        }
    }
}