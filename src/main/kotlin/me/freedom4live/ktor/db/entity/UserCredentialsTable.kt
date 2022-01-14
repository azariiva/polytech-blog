package me.freedom4live.ktor.db.entity

import org.jetbrains.exposed.sql.Table

object UserCredentialsTable : Table("user_credentials") {
    val username = varchar("username", 255)
    val password = varchar("password", 255)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(username)
}