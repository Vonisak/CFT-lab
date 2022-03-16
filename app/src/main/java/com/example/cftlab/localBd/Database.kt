package com.example.cftlab.localBd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Valute::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun valuteDao(): ValuteDao
}