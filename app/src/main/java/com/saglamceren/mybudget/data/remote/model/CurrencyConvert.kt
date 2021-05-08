package com.saglamceren.mybudget.data.remote.model

import com.google.gson.annotations.SerializedName

data class CurrencyConvert(
    @SerializedName("conversion_rate")
    val conversionRate: Double
)