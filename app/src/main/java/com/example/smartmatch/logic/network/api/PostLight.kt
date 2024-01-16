package com.example.smartmatch.logic.network.api

import com.example.smartmatch.logic.model.helper.PostLight1
import com.example.smartmatch.logic.network.model.PostLightRespose
import com.example.smartmatch.logic.network.model.UpCTAllResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostLight {
    @POST("instructions/light/0ff")
    fun PostLightData(@Body postLight: PostLight1): Call<PostLightRespose>
}