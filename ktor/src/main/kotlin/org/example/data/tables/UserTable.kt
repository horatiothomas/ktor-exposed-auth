package org.example.data.tables

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object UserTable : IntIdTable("users") {
  val username = varchar("username", 50).uniqueIndex()
  val password = varchar("password", 255)
}
