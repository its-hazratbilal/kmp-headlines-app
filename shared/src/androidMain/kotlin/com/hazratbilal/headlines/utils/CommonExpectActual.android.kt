package com.hazratbilal.headlines.utils

import android.content.Context
import android.content.Intent

private lateinit var appContext: Context

fun initShareManager(context: Context) {
    appContext = context.applicationContext
}

actual fun shareUrl(url: String) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    val shareIntent = Intent.createChooser(sendIntent, "Share Link")
    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    appContext.startActivity(shareIntent)
}

actual fun getGridColumns(windowWidth: Int): Int = calculateColumns(windowWidth)
