package com.saglamceren.mybudget.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TRY_table")
data class TRYEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "USD")
    val USD: Double,
    @ColumnInfo(name = "EUR")
    val EUR: Double,
    @ColumnInfo(name = "GBP")
    val GBP: Double,
)