package com.hazratbilal.headlines.data.local.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File
import java.util.Properties

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val dbFile = File(System.getProperty("user.home"), "headlines.db")
        val isNew = !dbFile.exists()
        val driver = JdbcSqliteDriver(
            url = "jdbc:sqlite:${dbFile.absolutePath}",
            properties = Properties()
        )
        if (isNew) {
            HeadlinesDatabase.Schema.create(driver)
        }
        return driver
    }
}