package org.example.user

import io.ktor.server.application.Application
import io.ktor.server.plugins.di.dependencies
import org.example.security.PasswordService
import org.example.user.data.UserExposedRepository
import org.jetbrains.exposed.v1.jdbc.Database

fun Application.userModule() {
  val database: Database by dependencies
  val passwordService: PasswordService by dependencies

  dependencies {
    provide<UserService> { UserService(passwordService, UserExposedRepository(database)) }
  }
}
