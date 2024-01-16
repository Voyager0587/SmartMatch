package com.example.smartmatch.logic.network.model

/**
 * @className: LightDataResponse
 * @author: Voyager
 * @description: TODO
 * @date:  2024/1/15 21:46
 * @version 1.0
 **/
data class LightDataResponse(
    val code: Int,
    val `data`: LightDetail,
    val msg: Any
)

data class LightDetail(
    val light_data: List<LightId>,
    val light_num: Int
)

data class LightId(
    val id: Int
)