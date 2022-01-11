package me.freedom4live.ktor

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*

fun Application.setupRoutes() {
    routing {
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

        install(StatusPages) { //Install special feature
            //Here you can specify responses on exceptions
            exception<AuthenticationException> { cause -> //We can specify any response for any exception type
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }
}