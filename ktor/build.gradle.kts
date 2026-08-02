plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(ktorLibs.plugins.ktor)
  alias(libs.plugins.kotlinx.serialization)
  alias(libs.plugins.ktfmt.gradle)
}

dependencies {
  implementation(ktorLibs.server.di)
  implementation(ktorLibs.server.core)
  implementation(ktorLibs.server.netty)
  implementation(ktorLibs.server.config.yaml)
  implementation(ktorLibs.server.auth)
  implementation(ktorLibs.server.sessions)
  implementation(ktorLibs.server.htmlBuilder)
  implementation(libs.logback.classic)
  implementation(libs.exposed.core)
  implementation(libs.exposed.dao)
  implementation(libs.exposed.jdbc)
}

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

application { mainClass.set("io.ktor.server.netty.EngineMain") }
