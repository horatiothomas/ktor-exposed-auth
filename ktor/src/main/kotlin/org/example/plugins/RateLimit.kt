package org.example.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.ratelimit.RateLimit
import kotlin.time.Duration.Companion.seconds

fun Application.rateLimit() {
  install(RateLimit) { global { rateLimiter(limit = 5, refillPeriod = 60.seconds) } }
}
