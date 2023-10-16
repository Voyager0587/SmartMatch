package com.example.smartmatch.logic.network

import com.example.smartmatch.SmartApplication
import okhttp3.OkHttpClient
import okhttp3.Request
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

    private const val BASE_URL = "http://175.178.88.228:8080"

    private var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("Authorization", SmartApplication.sp.getString("token","")!!)
                .build()
            chain.proceed(request)
        }
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T =
        retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}