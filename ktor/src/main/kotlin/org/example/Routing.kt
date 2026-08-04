package org.example

import io.ktor.resources.Resource
import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.plugins.di.dependencies
import io.ktor.server.plugins.ratelimit.rateLimit
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.routing.routing
import org.example.user.UserController

@Resource("/") class Index

@Resource("login") class Login

@Resource("sign-up") class SignUp(val error: String? = null)

@Resource("logout") class Logout

fun Application.routing() {
  val userController: UserController by dependencies

  routing {
    rateLimit {
      authenticate("auth-session") { get<Index> { userController.index(call) } }
      authenticate("auth-form") { post<Login> { userController.initUserSession(call) } }
      get<Login> { userController.showLogin(call) }
      get<SignUp> { signup -> userController.showSignUp(call, signup.error) }
      post<SignUp> { userController.signUp(call) }
      get<Logout> { userController.logout(call) }
    }
  }
}
