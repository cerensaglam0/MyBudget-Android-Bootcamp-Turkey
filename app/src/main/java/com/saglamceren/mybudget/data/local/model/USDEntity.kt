package com.saglamceren.mybudget.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USD_table")
data class USDEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "TRY")
    val TRY: Double,
    @ColumnInfo(name = "EUR")
    val EUR: Double,
    @ColumnInfo(name = "GBP")
    val GBP: Double,
)