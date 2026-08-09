package org.example.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.config.property
import io.ktor.server.sessions.SessionStorageMemory
import io.ktor.server.sessions.SessionTransportTransformerMessageAuthentication
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import kotlinx.serialization.Serializable

@Serializable data class UserSession(val userId: Int)

fun Application.sessions() {
  val hsSecret: String = property("hs_secret")
  val secureCookies: Boolean = property("secure_cookies")
  val secretSignKey = hsSecret.hexToByteArray()
  install(Sessions) {
    cookie<UserSession>("user_session", SessionStorageMemory()) {
      cookie.path = "/"
      cookie.secure = secureCookies
      transform(SessionTransportTransformerMessageAuthentication(secretSignKey))
    }
  }
}
