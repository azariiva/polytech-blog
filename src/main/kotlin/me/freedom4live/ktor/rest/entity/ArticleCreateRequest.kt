package me.freedom4live.ktor.rest.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class ArticleCreateRequest(
    @param:JsonProperty(required = true)
    val title: String,
    @param:JsonProperty("body")
    val text: String?
)