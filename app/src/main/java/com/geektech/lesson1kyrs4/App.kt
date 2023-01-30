package com.geektech.lesson1kyrs4

import android.app.Application
import androidx.room.Room
import com.geektech.lesson1kyrs4.data.db.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .build()
    }

    companion object {
        lateinit var db: AppDatabase
    }
}