package me.freedom4live.ktor.db


import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory
import java.util.*

fun Application.initDB() {
    val config = ConfigFactory.load().getConfig("ktor.hikariConfig")
    val dbConfig = HikariConfig(config.toProperties())
    val dataSource = HikariDataSource(dbConfig)
    Database.connect(dataSource)
    LoggerFactory.getLogger(this::class.simpleName).info("Initialized Database")
}

private fun Config.toProperties() = Properties().also {
    for (e in this.entrySet()) {
        it.setProperty(e.key, this.getString(e.key))
    }
}