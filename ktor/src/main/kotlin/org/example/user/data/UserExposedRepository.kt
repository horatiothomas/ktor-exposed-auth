package org.example.user.data

import org.example.data.ExposedRepository
import org.example.data.tables.UserTable
import org.example.user.NewUser
import org.example.user.User
import org.example.user.UsernameTakenException
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.exceptions.ExposedSQLException
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.insertReturning
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.postgresql.util.PSQLState

class UserExposedRepository(database: Database) : ExposedRepository(database), UserRepository {

  override suspend fun createUser(newUser: NewUser): User = suspendTransaction {
    runCatching {
          UserTable.insertReturning {
            it[username] = newUser.username
            it[password] = newUser.password
          }
        }
        .onFailure { exception ->
          (exception as? ExposedSQLException)?.run {
            if (sqlState == PSQLState.UNIQUE_VIOLATION.state) {
              throw UsernameTakenException()
            }
          } ?: throw exception
        }
        .getOrThrow()
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
