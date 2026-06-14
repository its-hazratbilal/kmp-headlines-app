import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqldelight)
}

sqldelight {
    databases {
        create("HeadlinesDatabase") {
            packageName.set("com.hazratbilal.headlines.data.local.db")
        }
    }
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    jvm()

    androidLibrary {
       namespace = "com.hazratbilal.headlines.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()

       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            // Ktor http client
            implementation(libs.ktor.client.android)
            // DataStore Android
            implementation(libs.datastore.preferences.android)
            // SqlDelight
            implementation(libs.sqldelight.android)
            // Koin DI
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.ktor.client.android)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Material Icons
            implementation(libs.compose.material.icons)
            // Navigation
            implementation(libs.androidx.navigation.compose)
            // Serialization
            implementation(libs.kotlinx.serialization.json)
            // Coil image loading
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            // Ktor http client
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.logging)
            // Datastore
            implementation(libs.datastore.preferences)
            // SqlDelight
            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines)
            // Koin DI
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            // Kermit for logging
            implementation(libs.kermit)
        }

        iosMain.dependencies {
            // Ktor http client
            implementation(libs.ktor.client.ios)
            // SqlDelight
            implementation(libs.sqldelight.native)
        }

        jvmMain.dependencies {
            // Ktor http client
            implementation(libs.ktor.client.java)
            // SqlDelight
            implementation(libs.sqldelight.jvm)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

    }
}

compose.resources {
    publicResClass = true
    generateResClass = always
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}