package org.example.user

import io.ktor.server.auth.UserPasswordCredential
import org.example.user.data.UserRepository

class UserService(private val userRepository: UserRepository) {

  suspend fun createUser(credential: UserPasswordCredential): User =
      credential
          .run { NewUser(name, password) }
          .let { newUser -> userRepository.createUser(newUser = newUser) }

  suspend fun findUserByUsername(username: String): User? =
      userRepository.findUserByUsername(username)
}
