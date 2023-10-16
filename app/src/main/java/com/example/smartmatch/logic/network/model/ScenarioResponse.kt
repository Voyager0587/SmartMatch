package com.example.smartmatch.logic.network.model

/**
 * @className: ScenarioResponse
 * @author: Voyager
 * @description: 照明控制POST工具类  接口ID:115300345   接口：/instructions/scenario/on
 * @date:  2023/10/14 12:53
 * @version 1.0
 **/
data class ScenarioResponse(
    val scenario: List<Scenario>,
    val switch: Int
)

data class Scenario(
    val id: Int,
    val percentage: Int
)