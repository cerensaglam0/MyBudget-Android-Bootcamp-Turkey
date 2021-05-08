package com.saglamceren.mybudget.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saglamceren.mybudget.data.local.dao.CurrencyDao
import com.saglamceren.mybudget.data.local.dao.SpendingDao
import com.saglamceren.mybudget.data.local.model.*

//Singleton => factory => static

@Database(entities = [Spending::class, TRYEntity::class, USDEntity::class, EUREntity::class, GBPEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun spendingDao(): SpendingDao
    abstract fun currencyDao(): CurrencyDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "spending_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}