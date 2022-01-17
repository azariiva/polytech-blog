package me.freedom4live.ktor.rest

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import me.freedom4live.ktor.common.exception.AuthenticationException
import me.freedom4live.ktor.common.exception.UserAlreadyExistException
import me.freedom4live.ktor.service.AuthProvider

fun Application.setupAuth() {
    install(Authentication) { // we enable the feature
        //Configure Authentication with cookies. We tell it to use session with specified container UserIdPrincipal as an auth data from session storage
        session<UserIdPrincipal>(AuthName.SESSION) { // AuthName.SESSION is just a constant it gives a name to our auth configuration
            challenge {
                // What to do if the user isn't authenticated
                throw AuthenticationException() //We throw an exception (we will catch it a bit later)
            }
            validate { session: UserIdPrincipal ->
                // If you need to do additional validation on session data, you can do so here.
                session //We assume: if we have a session, it's ok
            }
        }

        //Configure Authentication with login data
        form(AuthName.FORM) { //Specific configuration with different name
            userParamName = FormFields.USERNAME //We let it know what form field names are.
            passwordParamName =
                FormFields.PASSWORD //const val USERNAME = "username"; const val PASSWORD = "password" - for example
            challenge {
                // if failed to auth
                throw AuthenticationException()
            }
            validate { cred: UserPasswordCredential ->
                //Here you can do what ever you want. I just mocked AuthProvider with constants, see below...
                AuthProvider.tryAuth(cred.name, cred.password)
            }
        }

        form(AuthName.SIGNUP) {
            userParamName = FormFields.USERNAME
            passwordParamName = FormFields.PASSWORD
            challenge {
                throw UserAlreadyExistException()
            }
            validate { cred: UserPasswordCredential ->
                AuthProvider.tryRegister(cred.name, cred.password)
            }
        }
    }

    routing {
        route("/login") { //routing
            authenticate(AuthName.FORM) { //Apply auth configuration for forms
                post {
                    //Principal must not be null as we are authenticated
                    val principal =
                        call.principal<UserIdPrincipal>()!! // If auth configuration worked, it would have principal from AuthProvider

                    // Set the cookie to make session auth working
                    call.sessions.set(principal) // To keep the user logged it, we put his principal into session. It makes work the second auth configuration
                    call.respondRedirect("/")
                }
            }
        }

        route("/signup") {
            authenticate(AuthName.SIGNUP) {
                post {
                    val principal = call.principal<UserIdPrincipal>()!!
                    call.sessions.set(principal)
                    call.respondRedirect("/")
                }
            }
        }

        post("/logout") {
            call.sessions.clear<UserIdPrincipal>()
            call.respondRedirect("/")
        }
    }

    install(StatusPages) {
        exception<UserAlreadyExistException> { cause ->
            call.respond(HttpStatusCode.Conflict)
        }
        exception<AuthenticationException> { cause -> //We can specify any response for any exception type
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}