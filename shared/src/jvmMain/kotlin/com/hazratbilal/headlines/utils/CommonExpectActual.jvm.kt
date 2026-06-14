package com.hazratbilal.headlines.utils

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

actual fun getGridColumns(windowWidth: Int): Int = calculateColumns(windowWidth)

actual fun shareUrl(url: String) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}