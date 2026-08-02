plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(ktorLibs.plugins.ktor)
  alias(libs.plugins.kotlinx.serialization)
  alias(libs.plugins.ktfmt.gradle)
  alias(libs.plugins.exposed)
}

dependencies {
  implementation(ktorLibs.server.di)
  implementation(ktorLibs.server.core)
  implementation(ktorLibs.server.netty)
  implementation(ktorLibs.server.config.yaml)
  implementation(ktorLibs.server.auth)
  implementation(ktorLibs.server.sessions)
  implementation(ktorLibs.server.htmlBuilder)
  implementation(ktorLibs.server.rateLimit)
  implementation(libs.logback.classic)
  implementation(libs.postgresql)
  implementation(libs.exposed.core)
  implementation(libs.exposed.dao)
  implementation(libs.exposed.jdbc)
  implementation(libs.jbcrypt)
}

java { toolchain { languageVersion = JavaLanguageVersion.of(17) } }

application { mainClass.set("io.ktor.server.netty.EngineMain") }

exposed {
  migrations {
    tablesPackage.set("org.example.data.tables")
    databaseUrl.set(providers.environmentVariable("DB_URL"))
    databaseUser.set(providers.environmentVariable("DB_USERNAME"))
    databasePassword.set(providers.environmentVariable("DB_PASSWORD"))
  }
}
