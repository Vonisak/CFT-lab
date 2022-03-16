package com.example.cftlab.localBd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Valute")
data class Valute(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "NumCode") val numCode: String,
    @ColumnInfo(name = "CharCode") val charCode: String,
    @ColumnInfo(name = "Nominal") val nominal: Int,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Value") val value: Double,
    @ColumnInfo(name = "Previous") val previous: Double
)
