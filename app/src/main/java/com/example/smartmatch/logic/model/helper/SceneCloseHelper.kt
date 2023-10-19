package com.example.smartmatch.logic.model.helper

/**
 * @className: SceneCloseHelper
 * @author: Voyager
 * @description: TODO
 * @date:  2023/10/19 23:53
 * @version 1.0
 **/
data class SceneCloseHelper(
    val scenario: List<ScenarioChild>
)

data class ScenarioChild(
    val id: Int
)