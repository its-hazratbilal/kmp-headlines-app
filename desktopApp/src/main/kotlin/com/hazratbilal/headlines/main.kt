package com.hazratbilal.headlines

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import headlines.shared.generated.resources.Res
import headlines.shared.generated.resources.logo
import org.jetbrains.compose.resources.painterResource


fun main() = application {
    System.setProperty("skiko.renderApi", "OPENGL")

    Window(
        onCloseRequest = ::exitApplication,
        title = "Headlines",
        icon = painterResource(Res.drawable.logo),
        state = WindowState(
            position = WindowPosition(Alignment.Center)
        )
    ) {
        App()
    }
}