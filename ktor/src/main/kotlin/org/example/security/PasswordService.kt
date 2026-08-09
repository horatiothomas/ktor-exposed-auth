package org.example.security

const val PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d\\W_]{8,}$"

abstract class PasswordService {

  fun isPasswordSecure(password: String): Boolean = password.matches(PASSWORD_REGEX.toRegex())

  abstract suspend fun hashPassword(password: String): String

  abstract suspend fun verifyPassword(password: String, hashedPassword: String): Boolean
}
