package com.example.smartmatch.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @className: ServiceCreator
 * @author: Voyager
 * @description: service创建
 * @date:  2023/9/20 12:46
 * @version 1.0
 **/
object ServiceCreator {

    private const val BASE_URL = "https://mock.apifox.cn/m1/2970761-0-default/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T =
        retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}