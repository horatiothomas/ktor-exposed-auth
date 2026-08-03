package org.example

import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.plugins.di.dependencies
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.example.user.UserController

object Path {
  const val INDEX = "/"
  const val LOGIN = "login"
  const val SIGN_UP = "sign-up"
  const val LOGOUT = "logout"
}

fun Application.routing() {
  val userController: UserController by dependencies

  routing {
    authenticate("auth-session") { get(Path.INDEX) { userController.index(call) } }
    route(Path.LOGIN) {
      authenticate("auth-form") { post { userController.initUserSession(call) } }
      get { userController.showLogin(call) }
    }
    route(Path.SIGN_UP) {
      get { userController.showSignUp(call) }
      post { userController.signUp(call) }
    }
    get(Path.LOGOUT) { userController.logout(call) }
  }
}
