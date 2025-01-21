package com.rure.angkorlife.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val baseUrl = "https://api-wmu-dev.angkorcoms.com/"
    private val okHttpClient=  OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor()).build()
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val instance = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}