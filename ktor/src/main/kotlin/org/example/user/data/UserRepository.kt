package org.example.user.data

import org.example.user.NewUser
import org.example.user.User

interface UserRepository {

  // Create

  suspend fun createUser(newUser: NewUser): User

  // Read

  suspend fun findUserByUsername(username: String): User?
}
