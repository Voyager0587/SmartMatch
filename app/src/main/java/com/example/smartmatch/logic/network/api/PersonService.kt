package com.example.smartmatch.logic.network.api

import com.example.smartmatch.logic.model.User
import com.example.smartmatch.logic.network.model.ResponseMessage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @className: PersonService
 * @author: Voyager
 * @description: 个人界面服务
 * @date:  2023/10/16 13:59
 * @version 1.0
 **/
interface PersonService {
    @POST("login")
    fun login(@Body user: User): Call<ResponseMessage>
}