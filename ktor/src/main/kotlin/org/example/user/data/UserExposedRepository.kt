package org.example.user.data

import org.example.data.ExposedRepository
import org.example.user.NewUser
import org.example.user.User
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.insertReturning
import org.jetbrains.exposed.v1.jdbc.selectAll

class UserExposedRepository(database: Database) : ExposedRepository(database), UserRepository {

  override suspend fun createUser(newUser: NewUser): User = suspendTransaction {
    UserTable.insertReturning {
          it[username] = newUser.username
          it[password] = newUser.password
        }
        .single()
        .toUser()
  }

  override suspend fun findUserByUsername(username: String): User? = suspendTransaction {
    UserTable.selectAll().where { UserTable.username eq username }.limit(1).singleOrNull()?.toUser()
  }
}

private fun ResultRow.toUser() =
    User(
        id = this[UserTable.id].value,
        username = this[UserTable.username],
        password = this[UserTable.password],
    )
