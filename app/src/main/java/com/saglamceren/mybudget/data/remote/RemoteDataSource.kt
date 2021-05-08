package com.saglamceren.mybudget.data.remote

import com.saglamceren.mybudget.data.remote.model.CurrencyConvert
import com.saglamceren.mybudget.utils.ApiUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteDataSource {


    fun convertSingleCurrency(currencies: String): Observable<CurrencyConvert> {
        return ApiUtils.getApiService().convertCurrency(currencies)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}