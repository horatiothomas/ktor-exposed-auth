package org.example.user

import io.ktor.server.auth.UserPasswordCredential
import org.example.security.PasswordService
import org.example.user.data.UserRepository

class UserService(
    private val passwordService: PasswordService,
    private val userRepository: UserRepository,
) {

  suspend fun createUser(credential: UserPasswordCredential): User =
      credential
          .run { NewUser(name, passwordService.hashPassword(password)) }
          .let { newUser -> userRepository.createUser(newUser = newUser) }

  suspend fun findUserByUsername(username: String): User? =
      userRepository.findUserByUsername(username)

  suspend fun isPasswordValid(password: String, hashedPassword: String): Boolean =
      passwordService.verifyPassword(password = password, hashedPassword = hashedPassword)
}
