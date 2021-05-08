package com.saglamceren.mybudget.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saglamceren.mybudget.data.local.model.EUREntity
import com.saglamceren.mybudget.data.local.model.GBPEntity
import com.saglamceren.mybudget.data.local.model.TRYEntity
import com.saglamceren.mybudget.data.local.model.USDEntity

@Dao
interface CurrencyDao {

    // TRY
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTRY(data: TRYEntity)

    @Query("SELECT * FROM TRY_table LIMIT 1")
    fun getTRY(): TRYEntity

    // USD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUSD(data: USDEntity)

    @Query("SELECT * FROM USD_table LIMIT 1")
    fun getUSD(): USDEntity

    // EUR
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEUR(data: EUREntity)

    @Query("SELECT * FROM EUR_table LIMIT 1")
    fun getEUR(): EUREntity

    // GBP
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGBP(data: GBPEntity)

    @Query("SELECT * FROM GBP_table LIMIT 1")
    fun getGBP(): GBPEntity


}