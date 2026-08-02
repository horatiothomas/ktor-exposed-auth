package org.example.user

import io.ktor.server.auth.principal
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.RoutingContext
import io.ktor.server.sessions.clear
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import org.example.plugins.UserIdPrincipal
import org.example.plugins.UserSession
import org.example.user.view.index

suspend fun RoutingContext.initUserSession() {
  val userId = call.principal<UserIdPrincipal>()?.id
  call.sessions.set(UserSession(userId = userId!!))
  call.respondRedirect("/")
}

suspend fun RoutingContext.index() {
  val userSession = call.principal<UserSession>()
  index(userId = userSession!!.userId)
}

suspend fun RoutingContext.logout() {
  call.sessions.clear<UserSession>()
  call.respondRedirect("/login")
}
