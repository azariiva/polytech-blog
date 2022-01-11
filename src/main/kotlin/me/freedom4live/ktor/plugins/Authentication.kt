package me.freedom4live.ktor.plugins

import io.ktor.application.*
import io.ktor.auth.*
import me.freedom4live.ktor.AuthName
import me.freedom4live.ktor.AuthProvider
import me.freedom4live.ktor.AuthenticationException
import me.freedom4live.ktor.FormFields

fun Application.authentication() {
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
                // if field to auth
                throw AuthenticationException()
            }
            validate { cred: UserPasswordCredential ->
                //Here you can do what ever you want. I just mocked AuthProvider with constants, see below...
                AuthProvider.tryAuth(cred.name, cred.password)
            }
        }
    }
}