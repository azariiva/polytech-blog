package me.freedom4live.ktor.db.entity

import org.jetbrains.exposed.sql.ResultRow

data class UserCredentials(
    val username: String,
    val password: String
) {
    companion object {
        fun fromResultRow(row: ResultRow): UserCredentials =
            UserCredentials(
                username = row[UserCredentialsTable.username],
                password = row[UserCredentialsTable.password]
            )
    }
}