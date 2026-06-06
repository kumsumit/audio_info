import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("com.android.library")
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
            java.directories.add("src/main/kotlin")
        }

        getByName("test") {
            java.directories.add("src/test/kotlin")
        }
    }

    testOptions {

        unitTests.all {

            (this as Test).apply {

                useJUnitPlatform()

                testLogging {

                    events(
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
}


dependencies {

    testImplementation(kotlin("test"))

    testImplementation("org.mockito:mockito-core:5.23.0")
}