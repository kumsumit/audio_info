import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    kotlin("android")
}

group = "com.kumpali.audio_info"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

extensions.configure<LibraryExtension>("android") {

    namespace = "com.kumpali.audio_info"
    compileSdk = 37

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    sourceSets {
        getByName("main") {
            java.setSrcDirs(listOf("src/main/kotlin"))
        }

        getByName("test") {
            java.setSrcDirs(listOf("src/test/kotlin"))
        }
    }

    testOptions {
        unitTests.all {

            useJUnitPlatform()

            testLogging {
                events = setOf(
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.FAILED,
                    TestLogEvent.STANDARD_OUT,
                    TestLogEvent.STANDARD_ERROR
                )

                showStandardStreams = true
            }

            outputs.upToDateWhen { false }
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.mockito:mockito-core:5.23.0")
}