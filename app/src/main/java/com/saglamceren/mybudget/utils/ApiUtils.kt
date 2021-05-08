package com.saglamceren.mybudget.utils

import com.google.gson.Gson
import com.saglamceren.mybudget.data.remote.service.RemoteApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtils {

    private const val BASE_URL = "https://v6.exchangerate-api.com/v6/2ca57ff2708eebc178cf0f30/"
    private var retrofit: Retrofit? = null

    fun getApiService(): RemoteApiService {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(QueryInterceptor())

        if (retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                // .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit!!.create(RemoteApiService::class.java)
    }
}