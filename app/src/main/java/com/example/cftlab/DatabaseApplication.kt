package com.example.cftlab

import android.app.Application
import androidx.room.Room
import com.example.cftlab.localBd.Database

class DatabaseApplication: Application() {

    lateinit var database: Database

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, Database::class.java, "database-name")
            .fallbackToDestructiveMigration().build()
    }
}