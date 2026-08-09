@file:Suppress("UnstableApiUsage") // Added for dependencyResolutionManagement.repositories

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
  versionCatalogs {
    create("exposedLibs") { from("org.jetbrains.exposed:exposed-version-catalog:1.4.0") }
    create("ktorLibs") { from("io.ktor:ktor-version-catalog:3.5.2") }
  }
}

rootProject.name = "ktor-exposed-auth"
include("ktor")
