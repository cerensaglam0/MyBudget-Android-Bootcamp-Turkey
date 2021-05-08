package com.saglamceren.mybudget.data.remote.service

import com.saglamceren.mybudget.data.remote.model.CurrencyConvert
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteApiService {

    @GET("pair/{currencies}")
    fun convertCurrency(
        @Path("currencies") currencies: String
    ): Observable<CurrencyConvert>

}