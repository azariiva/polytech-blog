package me.freedom4live.ktor.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory

fun Application.initDB() {
    val configPath = environment.config.property("ktor.hikariConfig").getString()
    val dbConfig = HikariConfig(configPath)
    val dataSource = HikariDataSource(dbConfig)
    Database.connect(dataSource)
    LoggerFactory.getLogger(Application::class.simpleName).info("Initialized Database")
}