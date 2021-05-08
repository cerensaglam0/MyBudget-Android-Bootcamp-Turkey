package com.saglamceren.mybudget.ui.main

import com.saglamceren.mybudget.data.local.model.Spending

interface SpendingAdapterCallback {
    fun onSpendingItemClick(spending: Spending, moneyText: String)
}