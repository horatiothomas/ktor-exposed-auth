package org.example.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.form
import io.ktor.server.auth.session
import io.ktor.server.plugins.di.dependencies
import io.ktor.server.response.respondRedirect
import org.example.user.UserService

data class UserIdPrincipal(val id: Int)

fun Application.authentication() {
  val userService: UserService by dependencies

  install(Authentication) {
    form("auth-form") {
      userParamName = "username"
      passwordParamName = "password"
      validate { credentials ->
        val user =
            userService.findUserByUsername(credentials.name) ?: userService.createUser(credentials)
        if (
            // TODO: add ip rate limiting
            userService.isPasswordValid(
                password = credentials.password,
                hashedPassword = user.password,
            )
        ) {
          UserIdPrincipal(user.id)
        } else {
          null
        }
      }
    }
    session<UserSession>("auth-session") {
      validate { session -> session }
      challenge { call.respondRedirect("/login") }
    }
  }
}
