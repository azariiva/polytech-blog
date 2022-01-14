package me.freedom4live.ktor.db.repository

import me.freedom4live.ktor.db.entity.ArticleBodiesTable
import me.freedom4live.ktor.db.entity.ArticleBody
import me.freedom4live.ktor.rest.entity.ArticleCreateRequest
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.nio.ByteBuffer
import java.util.*

object ArticleBodiesExposedRepository {
    fun find(id: UUID): ArticleBody? =
        transaction {
            ArticleBodiesTable
                .select { ArticleBodiesTable.id eq id }
                .takeIf { !it.empty() }
                ?.let { ArticleBody.fromResultRow(it.first()) }
        }

    @Suppress("UNCHECKED_CAST")
    fun add(author: String, article: ArticleCreateRequest): UUID? =
        transaction {
            val fKey = ArticleBodiesTable.author.referee!! as Column<String>

            // check if author exist
            fKey.table
                .select { fKey eq author }
                .takeIf { !it.empty() }
                ?: return@transaction null

            ArticleBodiesTable
                .insert { body ->
                    body[ArticleBodiesTable.author] = author
                    body[title] = article.title
                    body[text] = article.text
                }
            exec("SELECT @last_article_id") {
                it.next()
                val bb = ByteBuffer.wrap(it.getBytes(1))
                UUID(bb.long, bb.long)
            }.also { commit() }
        }
}