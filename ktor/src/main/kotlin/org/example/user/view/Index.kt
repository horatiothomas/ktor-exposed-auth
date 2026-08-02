package org.example.user.view

import io.ktor.server.html.respondHtmlTemplate
import io.ktor.server.routing.RoutingContext
import kotlinx.html.a
import kotlinx.html.h1
import kotlinx.html.li
import kotlinx.html.nav
import kotlinx.html.ul
import org.example.LayoutTemplate

suspend fun RoutingContext.index(userId: Int) {
  call.respondHtmlTemplate(LayoutTemplate()) {
    pageTitle { +"Hello $userId" }
    content {
      nav { ul { li { a(href = "/logout") { +"Logout" } } } }
      h1 { +"Hello, $userId!" }
    }
  }
}
