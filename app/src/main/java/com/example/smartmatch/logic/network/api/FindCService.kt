package com.example.smartmatch.logic.network.api

import com.example.smartmatch.logic.model.helper.ScenarioChild
import com.example.smartmatch.logic.network.model.FindCData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FindCService {
    @GET("/mmnet/area/c/{id}")
    fun getCData(@Path("id") id: Int): Call<FindCData>
}
