package com.hazratbilal.headlines.utils

import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

actual fun getGridColumns(windowWidth: Int): Int = calculateColumns(windowWidth)

actual fun shareUrl(url: String) {
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}