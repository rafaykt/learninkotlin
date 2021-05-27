package me.rafael.yokota.shared.com.jetbrains.handson.kmm.shared.cache

import com.jetbrains.handson.kmm.shared.cache.AppDatabase
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "test.db")
    }
}