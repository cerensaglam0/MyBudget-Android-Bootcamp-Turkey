package com.saglamceren.mybudget.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "spending_table")
@Parcelize
data class Spending(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "money")
    var money: Double,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "category")
    var category: Int,
    @ColumnInfo(name = "currency")
    var currency: Int
) : Parcelable