package org.example.user.view

import io.ktor.server.html.respondHtmlTemplate
import io.ktor.server.routing.RoutingCall
import kotlinx.html.a
import kotlinx.html.h1
import kotlinx.html.li
import kotlinx.html.nav
import kotlinx.html.ul
import org.example.LayoutTemplate
import org.example.Path

suspend fun indexView(call: RoutingCall, userId: Int) {
  call.respondHtmlTemplate(LayoutTemplate()) {
    pageTitle { +"Hello $userId" }
    content {
      nav { ul { li { a(href = Path.LOGOUT) { +"Logout" } } } }
      h1 { +"Hello, $userId!" }
    }
  }
}
