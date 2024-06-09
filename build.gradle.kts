plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    alias(libs.plugins.pv.entrypoints)
    alias(libs.plugins.pv.kotlin.relocate)
    alias(libs.plugins.pv.java.templates)
}

dependencies {
    compileOnly(kotlin("stdlib-jdk8"))
    
    compileOnly(libs.plasmovoice)
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(8))

repositories {
    mavenCentral()
    maven("https://repo.plasmoverse.com/snapshots")
    maven("https://repo.plasmoverse.com/releases")
}

tasks {
    jar {
        enabled = false
    }

    shadowJar {
        archiveClassifier.set("")
    }
}
