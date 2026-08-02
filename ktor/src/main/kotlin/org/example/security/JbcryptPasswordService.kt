package org.example.security

import org.mindrot.jbcrypt.BCrypt

class JbcryptPasswordService : PasswordService {

  override fun isPasswordSecure(password: String): Boolean =
    with(password) {
      length >= 8 && any { it.isUpperCase() } && any { it.isLowerCase() } && any { it.isDigit() }
    }


  override suspend fun hashPassword(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt(12))
  }

  override suspend fun verifyPassword(password: String, hashedPassword: String): Boolean {
    return BCrypt.checkpw(password, hashedPassword)
  }
}
