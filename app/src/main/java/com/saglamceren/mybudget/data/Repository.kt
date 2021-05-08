package com.saglamceren.mybudget.data

import android.app.Application
import com.saglamceren.mybudget.data.local.LocalDataSource
import com.saglamceren.mybudget.data.local.model.*
import com.saglamceren.mybudget.data.remote.RemoteDataSource
import com.saglamceren.mybudget.data.remote.model.CurrencyConvert
import io.reactivex.Observable

class Repository(app: Application) {
    private val localDataSource by lazy { LocalDataSource(app) }
    private val remoteDataSource by lazy { RemoteDataSource() }

    /*
       Related with Room
    */

    suspend fun addSpending(spending: Spending) {
        localDataSource.addSpending(spending)
    }

    suspend fun deleteSpending(spendingId: Int) {
        localDataSource.deleteSpending(spendingId)
    }

    private fun updateTRYData(responseList: ArrayList<CurrencyConvert>) {
        localDataSource.addTRY(
            TRYEntity(
                USD = responseList[0].conversionRate,
                EUR = responseList[1].conversionRate,
                GBP = responseList[2].conversionRate
            )
        )
    }

    private fun updateUSDData(responseList: ArrayList<CurrencyConvert>) {
        localDataSource.addUSD(
            USDEntity(
                TRY = responseList[0].conversionRate,
                EUR = responseList[1].conversionRate,
                GBP = responseList[2].conversionRate
            )
        )
    }

    private fun updateEURData(responseList: ArrayList<CurrencyConvert>) {
        localDataSource.addEUR(
            EUREntity(
                TRY = responseList[0].conversionRate,
                USD = responseList[1].conversionRate,
                GBP = responseList[2].conversionRate
            )
        )
    }

    private fun updateGBPData(responseList: ArrayList<CurrencyConvert>) {
        localDataSource.addGBP(
            GBPEntity(
                TRY = responseList[0].conversionRate,
                USD = responseList[1].conversionRate,
                EUR = responseList[2].conversionRate
            )
        )
    }

    /*
        Related with remote
     */
    private fun getRequestParams(currency: Currency): ArrayList<String> {
        return when (currency) {
            Currency.TRY -> arrayListOf("USD/TRY", "EUR/TRY", "GBP/TRY")
            Currency.USD -> arrayListOf("TRY/USD", "EUR/USD", "GBP/USD")
            Currency.EUR -> arrayListOf("TRY/EUR", "USD/EUR", "GBP/EUR")
            Currency.GBP -> arrayListOf("TRY/GBP", "USD/GBP", "EUR/GBP")
        }
    }

    fun convertCurrency(currency: Currency): Observable<List<Spending>> {
        val requestParams = getRequestParams(currency)
        val responseList = ArrayList<CurrencyConvert>()

        return remoteDataSource.convertSingleCurrency(requestParams[0])
            .flatMap {
                responseList.add(it)
                remoteDataSource.convertSingleCurrency(requestParams[1])
            }.flatMap {
                responseList.add(it)
                remoteDataSource.convertSingleCurrency(requestParams[2])
            }.doOnNext {
                responseList.add(it)
            }.flatMap {
                when (currency) {
                    Currency.TRY -> updateTRYData(responseList)
                    Currency.USD -> updateUSDData(responseList)
                    Currency.EUR -> updateEURData(responseList)
                    Currency.GBP -> updateGBPData(responseList)
                }
                Observable.just(getSpendingList(currency))
            }.doOnError {
                throw CustomErrorException(getSpendingList(currency))
            }
    }

    fun getSpendingList(currency: Currency): List<Spending> {
        val spendingList = localDataSource.getSpendingList()

        when (currency) {
            Currency.TRY -> {
                val tryEntity = localDataSource.getTRY()
                for (spending in spendingList) {
                    when (spending.currency) {
                        Currency.USD.id -> {
                            spending.money = tryEntity.USD * spending.money
                        }
                        Currency.EUR.id -> {
                            spending.money = tryEntity.EUR * spending.money
                        }
                        Currency.GBP.id -> {
                            spending.money = tryEntity.GBP * spending.money
                        }
                    }
                }
            }
            Currency.USD -> {
                val usdEntity = localDataSource.getUSD()
                for (spending in spendingList) {
                    when (spending.currency) {
                        Currency.TRY.id -> {
                            spending.money = usdEntity.TRY * spending.money
                        }
                        Currency.EUR.id -> {
                            spending.money = usdEntity.EUR * spending.money
                        }
                        Currency.GBP.id -> {
                            spending.money = usdEntity.GBP * spending.money
                        }
                    }
                }
            }
            Currency.EUR -> {
                val eurEntity = localDataSource.getEUR()
                for (spending in spendingList) {
                    when (spending.currency) {
                        Currency.TRY.id -> {
                            spending.money = eurEntity.TRY * spending.money
                        }
                        Currency.USD.id -> {
                            spending.money = eurEntity.USD * spending.money
                        }
                        Currency.GBP.id -> {
                            spending.money = eurEntity.GBP * spending.money
                        }
                    }
                }
            }
            Currency.GBP -> {
                val gbpEntity = localDataSource.getGBP()
                for (spending in spendingList) {
                    when (spending.currency) {
                        Currency.TRY.id -> {
                            spending.money = gbpEntity.TRY * spending.money
                        }
                        Currency.USD.id -> {
                            spending.money = gbpEntity.USD * spending.money
                        }
                        Currency.EUR.id -> {
                            spending.money = gbpEntity.EUR * spending.money
                        }
                    }
                }
            }
        }

        return spendingList
    }


    /*
       Related with SharedPreferences
    */
    fun setInfo(name: String, gender: Int) {
        localDataSource.setInfo(name, gender)
    }

    fun getInfoText(): String {
        return localDataSource.getInfoText()
    }

    fun isFirstEntry(): Boolean {
        return localDataSource.isFirstEntry()
    }
}

class CustomErrorException(val spendingListFromLocal: List<Spending>) : Throwable()