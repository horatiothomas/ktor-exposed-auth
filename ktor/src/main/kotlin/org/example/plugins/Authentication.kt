package org.example.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.form
import io.ktor.server.auth.session
import io.ktor.server.plugins.di.dependencies
import io.ktor.server.response.respondRedirect
import org.example.user.UserService
import org.example.user.view.LoginForm

data class UserIdPrincipal(val id: Int)

fun Application.authentication() {
  val userService: UserService by dependencies

  install(Authentication) {
    form("auth-form") {
      userParamName = LoginForm.USERNAME
      passwordParamName = LoginForm.PASSWORD
      validate { credentials ->
        userService.findUserByUsername(credentials.name)?.let { user ->
          if (
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
    }
    session<UserSession>("auth-session") {
      validate { session -> session }
      challenge { call.respondRedirect("/login") }
    }
  }
}
