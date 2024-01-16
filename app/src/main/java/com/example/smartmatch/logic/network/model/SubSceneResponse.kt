package com.example.smartmatch.logic.network.model

/**
 * @className: SubSceneResponse
 * @author: Voyager
 * @description: TODO
 * @date:  2024/1/16 17:28
 * @version 1.0
 **/
data class SubSceneResponse(
    val code: Int,
    val `data`: List<Data3>,
    val msg: Any
)

data class Data3(
    val definitional_percentage: Double,
    val id: Int,
    val name: String
)