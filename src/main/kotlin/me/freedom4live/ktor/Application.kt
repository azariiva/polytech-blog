package me.freedom4live.ktor

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import me.freedom4live.ktor.plugins.authentication
import me.freedom4live.ktor.plugins.sessions

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args) // Starts as NettyEngine, it finds extension functions like module to let them bootstrap the application

fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    sessions()
    authentication()
    routing()
}

