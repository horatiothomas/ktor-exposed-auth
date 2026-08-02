package org.example.security

abstract class PasswordService {

  fun isPasswordSecure(password: String): Boolean = with(password) {
    length >= 8 && any { it.isUpperCase() } && any { it.isLowerCase() } && any { it.isDigit() }
  }

  abstract suspend fun hashPassword(password: String): String

  abstract suspend fun verifyPassword(password: String, hashedPassword: String): Boolean
}
