package com.example.smartmatch.logic.network.model

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