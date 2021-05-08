package com.saglamceren.mybudget.data.local

import android.app.Application
import com.saglamceren.mybudget.data.Gender
import com.saglamceren.mybudget.data.local.database.LocalDatabase
import com.saglamceren.mybudget.data.local.model.*
import com.saglamceren.mybudget.utils.PreferencesUtil

class LocalDataSource(app: Application) {
    private val database by lazy { LocalDatabase.getDatabase(app) }
    private val spendingDao by lazy { database.spendingDao() }
    private val currencyDao by lazy { database.currencyDao() }
    private val preferencesUtil by lazy { PreferencesUtil(app) }

    /*
        Related with Room
     */
    fun getSpendingList(): List<Spending> {
        return spendingDao.allSpendingList()
    }

    suspend fun deleteSpending(spendingId: Int) {
        spendingDao.deleteSpending(spendingId)
    }

    suspend fun addSpending(spending: Spending) {
        spendingDao.addingSpending(spending)
    }

    fun addTRY(data: TRYEntity) {
        currencyDao.addTRY(data)
    }

    fun getTRY(): TRYEntity {
        return currencyDao.getTRY()
    }

    fun addUSD(data: USDEntity) {
        currencyDao.addUSD(data)
    }

    fun getUSD(): USDEntity {
        return currencyDao.getUSD()
    }

    fun addEUR(data: EUREntity) {
        currencyDao.addEUR(data)
    }

    fun getEUR(): EUREntity {
        return currencyDao.getEUR()
    }

    fun addGBP(data: GBPEntity) {
        currencyDao.addGBP(data)
    }

    fun getGBP(): GBPEntity {
        return currencyDao.getGBP()
    }

    /*
        Related with SharedPreferences
     */
    fun setInfo(name: String, gender: Int) {
        preferencesUtil.setInfo(name, gender)
    }

    fun getInfoText(): String {
        val builder = StringBuilder()
        builder.append(preferencesUtil.getName())

        when (preferencesUtil.getGender()) {
            Gender.MALE.id -> builder.append(" Bey")
            Gender.FEMALE.id -> builder.append(" Hanım")
        }

        return builder.toString()
    }

    fun isFirstEntry(): Boolean {
        return preferencesUtil.isFirstEntry()
    }

}