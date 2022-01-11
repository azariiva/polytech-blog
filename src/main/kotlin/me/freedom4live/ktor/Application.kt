package me.freedom4live.ktor

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.sessions.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args) // Starts as NettyEngine, it finds extension functions like module to let them bootstrap the application

fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Sessions) { //Enable Sessions
        cookie<UserIdPrincipal>( //Use UserIdPrincipal as user data principal container, and identifying it via cookies
            Cookies.AUTH_COOKIE, // Just String constant with value: const val AUTH_COOKIE = "auth"
            storage = SessionStorageMemory() // We use default in-memory storage, but you can write your own realization and store sessions wherever you wish
        ) {
            cookie.path = "/"  // We cookies should work
            cookie.extensions["SameSite"] = "lax"
        }
    }

    // Configure ktor to use form data auth and register relevant routes
    setupAuth()

    // Register application routes
    setupRoutes()
}

