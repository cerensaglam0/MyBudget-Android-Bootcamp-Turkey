package com.saglamceren.mybudget.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.saglamceren.mybudget.data.local.model.Spending

@Dao
interface SpendingDao {
    @Insert
    suspend fun addingSpending(spending: Spending)

    @Query("SELECT * FROM spending_table ORDER BY id DESC")
    fun allSpendingList(): List<Spending>

    @Query("DELETE FROM spending_table WHERE id=:spendingId")
    suspend fun deleteSpending(spendingId: Int)
}