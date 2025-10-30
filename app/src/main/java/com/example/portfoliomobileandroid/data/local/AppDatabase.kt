package com.example.portfoliomobileandroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Project::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
}