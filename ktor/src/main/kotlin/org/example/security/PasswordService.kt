package org.example.security

interface PasswordService {

  suspend fun hashPassword(password: String): String

  suspend fun verifyPassword(password: String, hashedPassword: String): Boolean
}
