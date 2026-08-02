package org.example

import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.example.user.helloUser
import org.example.user.initUserSession
import org.example.user.view.loginView

fun Application.routing() {
  routing {
    authenticate("auth-session") { get("/") { helloUser() } }
    route("login") {
      authenticate("auth-form") { post { initUserSession() } }
      get { loginView() }
    }
  }
}
