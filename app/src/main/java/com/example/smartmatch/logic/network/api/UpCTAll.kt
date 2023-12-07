package com.example.smartmatch.logic.network.api


import com.example.smartmatch.logic.model.helper.UpCTAllHelper
import com.example.smartmatch.logic.network.model.UpCTAllResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UpCTAll {
    @POST("/mmnet/area/{id}")
    fun postupctData(@Body upCTAllHelper: UpCTAllHelper): Call<UpCTAllResponse>

}