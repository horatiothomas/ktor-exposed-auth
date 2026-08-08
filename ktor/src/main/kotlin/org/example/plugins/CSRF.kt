package org.example.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.csrf.CSRF

fun Application.csrf() {
  install(CSRF) {
    originMatchesHost()
  }
}
