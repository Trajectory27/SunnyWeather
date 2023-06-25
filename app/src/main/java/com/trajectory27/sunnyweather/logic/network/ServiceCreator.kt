package com.trajectory27.sunnyweather.logic.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Trajectory27
 * @description Retrofit构建器
 * @date 2023/6/16 17:03
 */
object ServiceCreator {

    private const val BASE_URL = "http://api.caiyunapp.com"

    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    init {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}