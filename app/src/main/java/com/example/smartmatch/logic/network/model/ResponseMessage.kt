package com.example.smartmatch.logic.network.model

import com.google.gson.annotations.SerializedName

/**
 * @className: ResponseMessage
 * @author: Voyager
 * @description: 接口返回数据
 * @date:  2023/10/14 13:30
 * @version 1.0
 **/
data class ResponseMessage(
    val code: Int,
    val `data`: Any,
    val msg: String,
    val name: String,
    val token:String
)
/**
 * 定义C返回T的数据类型
 */
data class UpCTAllResponse(
    @SerializedName("name")
    val name: String
)
data class PostLightRespose(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Any,
    @SerializedName("msg")
    val msg: String
)