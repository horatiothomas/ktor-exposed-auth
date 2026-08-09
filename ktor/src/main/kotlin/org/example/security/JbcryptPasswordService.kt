package org.example.security

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mindrot.jbcrypt.BCrypt

class JbcryptPasswordService : PasswordService() {

  override suspend fun hashPassword(password: String): String =
      withContext(Dispatchers.Default) { BCrypt.hashpw(password, BCrypt.gensalt(12)) }

  override suspend fun verifyPassword(password: String, hashedPassword: String): Boolean =
      withContext(Dispatchers.Default) { BCrypt.checkpw(password, hashedPassword) }
}
