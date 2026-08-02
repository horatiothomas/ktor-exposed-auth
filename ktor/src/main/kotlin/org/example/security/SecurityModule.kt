package org.example.security

import io.ktor.server.application.Application
import io.ktor.server.plugins.di.dependencies

fun Application.securityModule() {
  dependencies { provide<PasswordService> { JbcryptPasswordService() } }
}
