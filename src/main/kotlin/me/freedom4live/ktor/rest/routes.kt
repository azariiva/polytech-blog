package me.freedom4live.ktor.rest

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.freemarker.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import me.freedom4live.ktor.common.exception.*
import me.freedom4live.ktor.db.repository.ArticleBodiesExposedRepository
import me.freedom4live.ktor.rest.entity.ArticleCreateRequest
import java.util.*

fun Application.setupRoutes() {
    routing {
        static("/static") {
            resources("/static")
        }

        get("/") {
            call.respondRedirect("/feed")
        }

        get("feed/{username?}") {
            val username = call.parameters["username"]
            val sessionPrincipal = call.sessions.get<UserIdPrincipal>()

            call.respond(
                FreeMarkerContent(
                    "feed.ftl",
                    username?.let { author ->
                        mapOf(
                            "title" to "\uD83D\uDC64 ${author}'s blog",
                            "articles" to ArticleBodiesExposedRepository.find(author),
                            "userIdPrincipal" to sessionPrincipal
                        )
                    } ?: mapOf(
                        "title" to "\uD83C\uDFE0 Home feed",
                        "articles" to ArticleBodiesExposedRepository.find(),
                        "userIdPrincipal" to sessionPrincipal
                    )
                )
            )
        }

        get("/login") {
            call.respond(FreeMarkerContent("login.ftl", null))
        }

        route("/user_info") { // Routing
            authenticate(AuthName.SESSION) { //Apply the second auth configuration
                get {
                    //Principal must not be null as we are authenticated
                    val principal =
                        call.principal<UserIdPrincipal>()!! //As we put principal into session after login, it must be there
                    call.respond(HttpStatusCode.OK, principal) //Returns OK and the principal
                }
            }
        }

        route("/article/submit") {
            authenticate(AuthName.SESSION) {
                post {
                    val principal = call.principal<UserIdPrincipal>()!!
                    val body = call.receive<ArticleCreateRequest>()
                    val id = ArticleBodiesExposedRepository.add(principal.name, body) ?: throw UserNotFoundException()
                    call.respondRedirect("/article/${id}")
                }
            }
        }

        get("/article/{id}") {
            val articleID = try {
                UUID.fromString(call.parameters["id"])
            } catch (e: IllegalArgumentException) {
                throw ArticleNotFoundException()
            }
            val article = ArticleBodiesExposedRepository.find(articleID) ?: throw ArticleNotFoundException()
            val sessionPrincipal = call.sessions.get<UserIdPrincipal>()
            call.respond(
                FreeMarkerContent(
                    "article.ftl",
                    mapOf("article" to article, "userIdPrincipal" to sessionPrincipal)
                )
            )
        }

        install(StatusPages) { //Install special feature
            //Here you can specify responses on exceptions
            exception<AuthenticationException> { cause -> //We can specify any response for any exception type
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }
            exception<UserAlreadyExistException> { cause ->
                call.respond(HttpStatusCode.Conflict)
            }
            exception<UserNotFoundException> { cause ->
                call.respond(HttpStatusCode.PreconditionFailed)
            }
            exception<ArticleNotFoundException> { cause ->
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}