package me.freedom4live.ktor.db.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

object ArticleBodiesTable : Table("article_bodies") {
    val id = uuid(name = "id")
    val title = varchar(name = "title", length = 255)
    val author = varchar(name = "author", length = 255)
        .references(UserCredentialsTable.username)
    val text = text("text").nullable()
    val createdTimestamp = datetime("created_ts")

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}