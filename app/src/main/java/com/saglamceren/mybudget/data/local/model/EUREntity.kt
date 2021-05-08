package com.saglamceren.mybudget.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EUR_table")
data class EUREntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "TRY")
    val TRY: Double,
    @ColumnInfo(name = "USD")
    val USD: Double,
    @ColumnInfo(name = "GBP")
    val GBP: Double,
)