package org.example.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.v1.core.Transaction
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction as jdbcSuspendTransaction

abstract class ExposedRepository(private val database: Database) {

  protected suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
      withContext(Dispatchers.IO) { jdbcSuspendTransaction(database, statement = block) }
}
