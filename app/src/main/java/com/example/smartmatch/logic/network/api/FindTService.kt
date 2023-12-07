package com.example.smartmatch.logic.network.api

import com.example.smartmatch.logic.model.helper.FindTHelper
import com.example.smartmatch.logic.network.model.FindTData
import com.example.smartmatch.logic.network.model.ResponseMessage
import retrofit2.Call
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST


interface FindTService {
    @POST("/mmnet/area/light")
    fun postTData(@Body findTData: FindTHelper): Call<FindTData>
}
