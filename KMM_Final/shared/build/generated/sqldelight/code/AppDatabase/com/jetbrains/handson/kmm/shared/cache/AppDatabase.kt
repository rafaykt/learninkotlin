package com.jetbrains.handson.kmm.shared.cache

import com.jetbrains.handson.kmm.shared.cache.shared.newInstance
import com.jetbrains.handson.kmm.shared.cache.shared.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import comjetbrainshandsonkmmsharedcache.AppDatabaseQueries

interface AppDatabase : Transacter {
  val appDatabaseQueries: AppDatabaseQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = AppDatabase::class.schema

    operator fun invoke(driver: SqlDriver): AppDatabase = AppDatabase::class.newInstance(driver)}
}
