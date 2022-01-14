package me.freedom4live.ktor.db.repository

import me.freedom4live.ktor.db.entity.UserCredentials
import me.freedom4live.ktor.db.entity.UserCredentialsTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UserCredentialsExposedRepository {
    fun find(username: String): UserCredentials? =
        transaction {
            UserCredentialsTable
                .select { UserCredentialsTable.username eq username }
                .takeIf { !it.empty() }
                ?.let { UserCredentials.fromResultRow(it.first()) }
        }

    fun add(userCredentials: UserCredentials) {
        transaction {
            UserCredentialsTable.insert { body ->
                body[username] = userCredentials.username
                body[password] = userCredentials.password
            }
            commit()
        }
    }
}