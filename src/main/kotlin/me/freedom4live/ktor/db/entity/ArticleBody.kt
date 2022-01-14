package me.freedom4live.ktor.db.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.jetbrains.exposed.sql.ResultRow
import org.joda.time.DateTime
import java.util.*

data class ArticleBody(
    @get:JsonIgnore
    val id: UUID,
    val title: String,
    val author: String,
    val text: String?,
    val createdTimestamp: DateTime
) {
    companion object {
        fun fromResultRow(row: ResultRow): ArticleBody =
            ArticleBody(
                id = row[ArticleBodiesTable.id],
                title = row[ArticleBodiesTable.title],
                author = row[ArticleBodiesTable.author],
                text = row[ArticleBodiesTable.text],
                createdTimestamp = row[ArticleBodiesTable.createdTimestamp]
            )
    }
}