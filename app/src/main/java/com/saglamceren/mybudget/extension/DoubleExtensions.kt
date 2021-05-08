package com.saglamceren.mybudget.extension

import com.saglamceren.mybudget.data.Currency
import java.text.NumberFormat

fun Double.toMoney(currency: Currency): String {
    val numberFormat = NumberFormat.getCurrencyInstance(currency.local)
    numberFormat.maximumFractionDigits = 2
    numberFormat.minimumFractionDigits = 2
    return numberFormat.format(this)
}