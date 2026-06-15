import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(projects.shared)

    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)

    implementation(libs.compose.uiToolingPreview)
    // To use resources from shared drawable
    implementation(libs.compose.components.resources)
}

compose.desktop {
    application {
        mainClass = "com.hazratbilal.headlines.MainKt"

        jvmArgs += listOf(
            "--add-opens", "java.base/sun.misc=ALL-UNNAMED",
            "--add-opens", "java.base/java.lang=ALL-UNNAMED",
            "--add-opens", "java.base/java.nio=ALL-UNNAMED",
            "--add-exports", "java.base/sun.misc=ALL-UNNAMED"
        )

        buildTypes.release.proguard {
            isEnabled.set(false)
        }

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.hazratbilal.headlines"
            packageVersion = "1.0.0"

            modules(
                "java.sql",
                "java.net.http",
                "jdk.crypto.ec",
                "jdk.unsupported"
            )

        }
    }
}