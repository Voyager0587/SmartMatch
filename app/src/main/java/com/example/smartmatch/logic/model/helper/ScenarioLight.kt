package com.example.smartmatch.logic.model.helper

/**
 * @className: ScenarioLight
 * @author: Voyager
 * @description: TODO
 * @date:  2024/1/15 21:53
 * @version 1.0
 **/
data class ScenarioLight(
    val scenario: List<Scenario>
)

data class Scenario(
    val id: Int,
    val percentage: Double
)