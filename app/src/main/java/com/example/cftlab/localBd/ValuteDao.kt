package com.example.cftlab.localBd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ValuteDao {
    @Insert
    fun insert(valute: Valute)

    @Insert
    fun insertAll(valutes: List<Valute>)

    @Query("SELECT * FROM valute")
    fun getAll(): List<Valute>

    @Query("DELETE FROM valute")
    fun cleanAll()
}