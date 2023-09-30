package com.example.smartmatch.logic.network

import android.location.LocationRequest
import android.util.Log
import com.example.smartmatch.logic.network.api.ConstructionService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import retrofit2.http.Body
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @className NetworkCenter
 * @description NetworkCenter
 * @author Voyager
 * @date 2023/9/22 21:15
 */

object NetworkCenter {

   // private val authServer = ServiceCreator.create<AuthService>()
    private val constructionServer = ServiceCreator.create<ConstructionService>()

    /**
     * Construction
     */

    /**
     * 获取MMNet全部数据
     */
    suspend fun getMMNetData()
        = constructionServer.getMMNetAllData().await()

    fun createNewArea(id:Int,name:String)
        = constructionServer.createNewArea(id,name)

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
//                    ToDo: delete this
                    Log.e("NetworkCenter", response.toString())
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}