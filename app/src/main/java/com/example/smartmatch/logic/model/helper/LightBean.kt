package com.example.smartmatch.logic.model.helper

/**
 * @className: LightBean
 * @author: Voyager
 * @description: TODO
 * @date:  2024/1/15 21:24
 * @version 1.0
 **/
data class LightBean(
    val light: List<LightDetail>
)

data class LightDetail(
    val id: Int,
    val percentage: Double
)