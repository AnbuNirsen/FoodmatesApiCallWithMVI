package com.example.foodmatesapicallwithmvi.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getRetrofit():Retrofit{
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(AuthInterceptor())

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        okHttpClient.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl("http://13.126.249.141/discover/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient.build())
            .build()
    }
}