package org.example.security

interface PasswordService {

  fun isPasswordSecure(password: String): Boolean

  suspend fun hashPassword(password: String): String

  suspend fun verifyPassword(password: String, hashedPassword: String): Boolean
}
