package com.saglamceren.mybudget.data

import java.util.*

enum class Currency(val id: Int, val local: Locale) {
    TRY(1, Locale("tr", "TR")),
    USD(2, Locale.US),
    EUR(3, Locale.GERMANY),
    GBP(4, Locale.UK)
}