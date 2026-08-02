package org.example.security

import org.mindrot.jbcrypt.BCrypt

class JbcryptPasswordService : PasswordService {

  override suspend fun hashPassword(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt(12))
  }

  override suspend fun verifyPassword(password: String, hashedPassword: String): Boolean {
    return BCrypt.checkpw(password, hashedPassword)
  }
}
