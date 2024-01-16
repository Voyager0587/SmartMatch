package com.example.smartmatch.logic.network.model

/**
 * @className: ScenarioDetailResponse
 * @author: Voyager
 * @description: TODO
 * @date:  2024/1/11 16:58
 * @version 1.0
 **/
data class ScenarioDetailResponse(
    val code: Int,
    val data: DetailData?,
    val msg: String?
)

data class DetailData(
    val desc: String?,
    val light_data: List<DetailLightData>?,
    val scenario_data: List<ScenarioData>?
)

data class DetailLightData(
    val definitional_percentage: Double,
    val id: Int
)

data class ScenarioData(
    val definitional_percentage: Double?,
    val id: Int?,
    val name: String?
)

