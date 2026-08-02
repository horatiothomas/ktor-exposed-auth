package org.example.user

import io.ktor.server.auth.principal
import io.ktor.server.response.respondRedirect
import io.ktor.server.response.respondText
import io.ktor.server.routing.RoutingContext
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import org.example.plugins.UserIdPrincipal
import org.example.plugins.UserSession

suspend fun RoutingContext.initUserSession() {
  val userId = call.principal<UserIdPrincipal>()?.id
  call.sessions.set(UserSession(userId = userId!!))
  call.respondRedirect("/")
}

suspend fun RoutingContext.helloUser() {
  val userSession = call.principal<UserSession>()
  call.respondText("Hello ${userSession!!.userId}!")
}
