package org.example.data

import io.ktor.server.application.Application
import io.ktor.server.config.property
import io.ktor.server.plugins.di.dependencies
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.jdbc.Database

@Serializable data class DatabaseConfig(val url: String, val username: String, val password: String)

fun Application.databaseModule() {
  val databaseConfig: DatabaseConfig = property("database")
  dependencies {
    provide<Database> {
      Database.connect(databaseConfig.url, databaseConfig.username, databaseConfig.password)
    }
  }
}
