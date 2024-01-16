package com.example.smartmatch.logic.network.api

import com.example.smartmatch.logic.network.model.FindCData
import com.example.smartmatch.logic.network.model.choicetdata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChoiceTService {
    @GET("/mmnet/scenario/light/{id}")
    fun getchoicetdata(@Path("id") id: Int): Call<choicetdata>
}