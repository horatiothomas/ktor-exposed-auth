package org.example.user

interface UserRepository {

  // Create

  suspend fun createUser(newUser: NewUser): User

  // Read

  suspend fun findUserByUsername(username: String): User?
}
