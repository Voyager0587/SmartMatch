package com.example.smartmatch.logic.network.model

/**
 * @className: SceneCreationResponse
 * @author: Voyager
 * @description: TODO
 * @date:  2023/10/19 15:49
 * @version 1.0
 **/
data class SceneCreationResponse(
    val code: Int,
    val `data`: Data,
    val msg: Any
)

data class Data(
    val light_data: List<LightData>,
    val light_num: Int
)

data class LightData(
    val id: Int
)