package com.example.smartmatch.logic.model.helper

/**
 * @className: ScenarioBean
 * @author: Voyager
 * @description: TODO
 * @date:  2024/1/16 12:12
 * @version 1.0
 **/
data class ScenarioBean(
    val scenario: List<ScenarioDetail>
)

data class ScenarioDetail(
    val id: Int,
    val percentage: Double
)